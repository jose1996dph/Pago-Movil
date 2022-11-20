package com.example.pagomovil.viewModels

import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pagomovil.BuildConfig
import com.example.pagomovil.models.Bank
import com.example.pagomovil.models.Contact
import com.example.pagomovil.usecases.ContactUsecase
import com.example.pagomovil.usecases.PaymentUsecase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class PayViewModel (
        private val paymentUsecase: PaymentUsecase,
        private val contactUsecase: ContactUsecase
) : ViewModel() {

    var contactId : Int = 0

    var dni: MutableLiveData<String> =  MutableLiveData<String>()
    var phone: MutableLiveData<String> =  MutableLiveData<String>()
    var mount: MutableLiveData<String> =  MutableLiveData<String>()
    var contactPosition: MutableLiveData<Int> = MutableLiveData<Int>()
    var operatorPosition: MutableLiveData<Int> =  MutableLiveData<Int>()
    var bankPosition: MutableLiveData<Int> = MutableLiveData<Int>()
    var name: MutableLiveData<String> = MutableLiveData<String>()
    var saveContact: MutableLiveData<Boolean> = MutableLiveData<Boolean>()

    private val banks: MutableLiveData<List<Bank>> = MutableLiveData<List<Bank>>()
    val operators: MutableLiveData<List<String>> = MutableLiveData<List<String>>()
    private val _contacts = contactUsecase.getAllContacts()
    val contacts: LiveData<List<Contact>> get() = _contacts

    init {
        banks.value = ArrayList<Bank>().apply {
            add(Bank("100% Banco", "0156"))
            add(Bank("Bancamiga", "0172"))
            add(Bank("Bancaribe", "0114"))
            add(Bank("Banco Agrícola de Venezuela", "0166"))
            add(Bank("Banco Caroní", "0128"))
            add(Bank("Banco de Venezuela, S.A.C.A.", "0102"))
            add(Bank("Banco del Tesoro", "0163"))
            add(Bank("Banco Plaza", "0138"))
            add(Bank("Bancrecer", "0168"))
            add(Bank("Banesco", "0134"))
            add(Bank("Banfanb", "0177"))
            add(Bank("Banplus", "0174"))
            add(Bank("BFC Banco Fondo Común", "0151"))
            add(Bank("Bicentenario del Pueblo", "0175"))
            add(Bank("BNC Nacional de Crédito", "0191"))
            add(Bank("Del Sur", "0157"))
            add(Bank("Exterior", "0115"))
            add(Bank("Mercantil", "0105"))
            add(Bank("Mi Banco", "0169"))
            add(Bank("Occidental de Descuento", "0116"))
            add(Bank("Provincial", "0108"))
            add(Bank("Venezolano de Crédito", "0104"))
        }
        operators.value = ArrayList<String>().apply {
            add("0412")
            add("0414")
            add("0424")
            add("0416")
            add("0426")
        }
    }

    fun getBank(position: Int): Bank{
        return banks.value?.get(position) ?: Bank("","")
    }

    fun getBanksName(): List<String>{
        return banks.value?.map { it.name } ?: emptyList<String>()
    }

    fun selectContact(contact: Contact) {
        contactId = contact.id

        banks.value!!.let {
            for (i in 1..it.size){
                if (contact.bank == it[i].number){
                    bankPosition.value = i
                    break
                }
            }
        }

        operatorPosition.value = operators.value!!.indexOf(contact.operator)
        dni.value = contact.dni
        phone.value = contact.phone
        name.value = contact.name
    }

    fun deleteContact(contact: Contact){
        contactUsecase.deleteContact(contact)
    }

    fun doPay(view : View) {
        val number: String? = banks.value?.get(bankPosition.value ?: 0)?.number
        val operator: String? =  operators.value?.get(operatorPosition.value ?: 0)

        try {
            paymentUsecase.makePayment(
                number ?: "",
                dni.value ?: "",
                mount.value ?: "",
                operator ?: "",
                phone.value ?: "",
                saveContact.value ?: false,
                name.value ?: "",
                    contactId
            )

            this.phone.value = ""
            this.dni.value = ""
            this.mount.value = ""
            this.name.value = ""
            this.saveContact.value = false
            this.contactId = 0

            Toast.makeText(view.context, "Procesando...", Toast.LENGTH_LONG).show()
        } catch (e: IllegalAccessException) {
            Toast.makeText(view.context, e.message, Toast.LENGTH_LONG).show()
            Log.e("ERROR", e.message ?: "")
        } catch (e: Exception) {
            if (BuildConfig.DEBUG) {
                Toast.makeText(view.context, e.message, Toast.LENGTH_LONG).show()
            }
            Log.e("ERROR", e.message ?: "")
        }
    }
}