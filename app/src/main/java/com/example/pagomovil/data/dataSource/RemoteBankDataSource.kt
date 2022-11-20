package com.example.pagomovil.data.dataSource

import com.example.pagomovil.models.Payment
import com.example.pagomovil.models.PaymentService

interface RemoteBankDataSource {
    fun getBalance(queryType: String)
    fun makePayment(payment: Payment)
    fun payService(paymentService: PaymentService)
}