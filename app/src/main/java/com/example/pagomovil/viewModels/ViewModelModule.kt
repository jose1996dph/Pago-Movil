package com.example.pagomovil.viewModels

import com.example.pagomovil.usecases.BalanceUsecase
import com.example.pagomovil.usecases.ContactUsecase
import com.example.pagomovil.usecases.PaymentUsecase
import dagger.Module
import dagger.Provides
import dagger.Subcomponent

@Module
class ViewModelModule {
    @Provides
    fun payViewModelProvider(
            paymentUsecase: PaymentUsecase,
            contactUsecase: ContactUsecase
    ) = PayViewModel(paymentUsecase, contactUsecase)

    @Provides
    fun  balanceViewModel(
        balanceUsecase: BalanceUsecase
    ) = BalanceViewModel(balanceUsecase)

    @Provides
    fun servicesViewModel(
        paymentUsecase: PaymentUsecase
    ) = ServicesViewModel(paymentUsecase)
}

@Subcomponent(modules = [(ViewModelModule::class)])
interface  ViewModelComponent{
    val payViewModel: PayViewModel
    val balanceViewModel: BalanceViewModel
    val servicesViewModel: ServicesViewModel
}