package com.example.pagomovil

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.example.pagomovil.viewModels.DaggerPagoMovilComponent
import com.example.pagomovil.viewModels.PagoMovilComponent

class PagoMovilApp: Application() {

    lateinit var component: PagoMovilComponent
        private set


    override fun onCreate() {
        super.onCreate()

        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)

        component = initAppComponent()
    }

    private fun initAppComponent() = DaggerPagoMovilComponent.factory().create(this)
}