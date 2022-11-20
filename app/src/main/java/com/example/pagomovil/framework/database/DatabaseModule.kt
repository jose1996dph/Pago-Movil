package com.example.pagomovil.framework.database

import android.app.Application
import com.example.pagomovil.data.dataSource.LocalContactDataSource
import com.example.pagomovil.data.dataSource.LocalServiceDataSource
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {
    @Provides
    @Singleton
    fun getDataBaseProvider(app: Application): AppDatabase = AppDatabase.getDatabase(app)

    @Provides
    fun getLocalContactDataSourceProvider(
            database: AppDatabase
    ): LocalContactDataSource = DatabaseContactDataSource(database)

    @Provides
    fun  getLocalServiceDataSourceProvider(
            database: AppDatabase
    ): LocalServiceDataSource = DatabaseServiceDataSource(database)
}