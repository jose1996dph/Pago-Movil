package com.example.pagomovil.framework.SMS

import com.example.pagomovil.data.dataSource.RemoteBankDataSource
import dagger.Module
import dagger.Provides

@Module
class SMSModule {
    @Provides
    fun getBankSMSDataSourceProvider(): RemoteBankDataSource = BankSMSDataSource()
}