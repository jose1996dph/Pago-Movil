package com.example.pagomovil.framework.SMS

import com.example.pagomovil.data.dataSource.RemoteBankDataSource
import com.example.pagomovil.models.Payment
import com.example.pagomovil.models.PaymentService

class BankSMSDataSource: RemoteBankDataSource {
    private val appSMS: AppSMS by lazy { AppSMS() }

    override fun getBalance(queryType: String) {
        appSMS.sendMessage(queryType)
    }

    override fun makePayment(payment: Payment) {
        val text = "Pagar ${payment.bank} ${payment.operator}${payment.phone} ${payment.dni} ${payment.mount}"
        appSMS.sendMessage(text)
    }

    override fun payService(paymentService: PaymentService) {
        val text = "Servicio ${paymentService.service} ${paymentService.alias} ${paymentService.mount}"
        appSMS.sendMessage(text)
    }
}