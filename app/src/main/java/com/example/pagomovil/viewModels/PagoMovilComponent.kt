package com.example.pagomovil.viewModels

import android.app.Application
import com.example.pagomovil.data.repository.RepositoryModule
import com.example.pagomovil.framework.SMS.SMSModule
import com.example.pagomovil.framework.database.DatabaseModule
import com.example.pagomovil.usecases.UseCaseModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    DatabaseModule::class,
    SMSModule::class,
    RepositoryModule::class,
    UseCaseModule::class
])
interface PagoMovilComponent {

    fun inject(module: ViewModelModule): ViewModelComponent

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance App: Application): PagoMovilComponent
    }
}