package com.example.pagomovil.usecases

import com.example.pagomovil.data.repository.BankRepository
import com.example.pagomovil.data.repository.ContactRepository
import com.example.pagomovil.data.repository.ServiceRepository
import dagger.Module
import dagger.Provides

@Module
class UseCaseModule {
    @Provides
    fun balanceUsecaseProvider(bankRepository: BankRepository): BalanceUsecase =
        BalanceUsecase(bankRepository)

    @Provides
    fun contactUsecaseProvider(contactRepository: ContactRepository): ContactUsecase =
            ContactUsecase(contactRepository)

    @Provides
    fun paymentUsecaseProvider(
            bankRepository: BankRepository,
            contactRepository: ContactRepository,
            serviceRepository: ServiceRepository
    ): PaymentUsecase = PaymentUsecase(bankRepository, contactRepository, serviceRepository)
}