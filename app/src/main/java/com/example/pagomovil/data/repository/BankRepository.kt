package com.example.pagomovil.data.repository

import com.example.pagomovil.data.dataSource.RemoteBankDataSource
import com.example.pagomovil.models.Payment
import com.example.pagomovil.models.PaymentService

class BankRepository (private val remoteBankDataSource: RemoteBankDataSource) {
    fun getBalance(queryType: String) =
        remoteBankDataSource.getBalance(queryType)

    fun makePayment(payment: Payment) =
        remoteBankDataSource.makePayment(payment)

    fun payService(paymentService: PaymentService) =
        remoteBankDataSource.payService(paymentService)
}