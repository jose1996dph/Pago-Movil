package com.example.pagomovil.usecases

import com.example.pagomovil.data.repository.BankRepository
import com.example.pagomovil.data.repository.ContactRepository
import com.example.pagomovil.data.repository.ServiceRepository
import com.example.pagomovil.models.Contact
import com.example.pagomovil.models.Service
import com.example.pagomovil.models.Payment
import com.example.pagomovil.models.PaymentService
import android.app.Service as Service1

@Suppress("NAME_SHADOWING")
class PaymentUsecase (
    private val bankRepository: BankRepository,
    private val contactRepository: ContactRepository,
    private val serviceRepository: ServiceRepository,
) {
    fun makePayment(
        bank: String,
        dni: String,
        mount: String,
        operator: String,
        phone: String,
        saveContact: Boolean,
        name: String,
        contactId : Int
    ) {
        if (bank.isEmpty() ||
                dni.isEmpty() ||
                mount.isEmpty() ||
                operator.isEmpty() ||
                phone.isEmpty() ||
                (saveContact && name.isEmpty())
        ){
            throw IllegalAccessException("Campo inválido")
        }

        if (saveContact) {
            val contact = Contact(
                    contactId,
                    name,
                    bank,
                    dni,
                    operator,
                    phone
            )
            contactRepository.saveContact( contact )
        }

        val payment = Payment(
                bank,
                dni,
                editMount(mount),
                operator,
                phone
        )

        bankRepository.makePayment(payment)
    }

    fun payService(service: String, alias: String, mount: String, name: String, saveService: Boolean){
        if (service.isEmpty() ||
                alias.isEmpty() ||
                mount.isEmpty()
        ){
            throw IllegalAccessException("Campo inválido")
        }

        if (saveService){
            val service = Service(
                    0,
                    service,
                    name,
                    alias,
            )
            serviceRepository.saveService(service)
        }

        val paymentService = PaymentService(
            service,
            alias,
            editMount(mount)
        )

        bankRepository.payService(paymentService)
    }


    private fun editMount(mount: String): String {
        var mount: String = mount
        mount = mount.replace(",", "")
        mount = mount.replace(".", ",")

        if (mount.contains(",")) {
            val mounts = mount.split(",".toRegex()).toTypedArray()
            val decimal = mounts[mounts.size - 1]
            mount = mounts[0] + ","

            for (i in 0..1) {
                mount += if (decimal.length > i) decimal[i] else "0"
            }
        } else {
            mount += ",00"
        }
        return mount
    }
}