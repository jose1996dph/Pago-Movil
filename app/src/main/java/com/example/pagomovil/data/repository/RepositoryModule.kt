package com.example.pagomovil.data.repository

import com.example.pagomovil.data.dataSource.LocalContactDataSource
import com.example.pagomovil.data.dataSource.LocalServiceDataSource
import com.example.pagomovil.data.dataSource.RemoteBankDataSource
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {
    @Provides
    fun getBankRepositoryProvider(remoteBankDataSource: RemoteBankDataSource) =
            BankRepository(remoteBankDataSource)

    @Provides
    fun getContactRepositoryProvider(localContactDataSource: LocalContactDataSource) =
            ContactRepository(localContactDataSource)

    @Provides
    fun getServiceRepositoryProvider(localServiceDataSource: LocalServiceDataSource) =
            ServiceRepository(localServiceDataSource)
}