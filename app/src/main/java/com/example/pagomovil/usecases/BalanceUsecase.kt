package com.example.pagomovil.usecases

import com.example.pagomovil.data.repository.BankRepository

class BalanceUsecase (
        private val bankRepository: BankRepository
) {
    fun getBalance(queryType: String) =  bankRepository.getBalance(queryType)
}