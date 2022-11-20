package com.example.pagomovil.viewModels

import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pagomovil.models.BanksNumber
import com.example.pagomovil.usecases.ContactUsecase
import com.example.pagomovil.usecases.PaymentUsecase

class ServicesViewModel(
        private val paymentUsecase: PaymentUsecase
) : ViewModel() {
    var name: MutableLiveData<String> =  MutableLiveData<String>()
    var alias: MutableLiveData<String> =  MutableLiveData<String>()
    var mount: MutableLiveData<String> =  MutableLiveData<String>()
    var servicePosition: MutableLiveData<Int> = MutableLiveData<Int>()
    var saveService: MutableLiveData<Boolean> = MutableLiveData<Boolean>()

    val services: MutableLiveData<List<String>> = MutableLiveData<List<String>>()

    init {
        services.value = ArrayList<String>().apply {
            add("Movistar")
            add("Digitel")
            add("Cantv")
            add("Movilnet")
            add("Gas")
            add("Agua")
            add("Luz")
            add("Tv")
            add("Internet")
        }
    }


    fun doPay(view : View){
        val service: String =  services.value?.get(servicePosition.value ?: 0) ?: ""

        try {

            paymentUsecase.payService(
                    service,
                    alias.value ?: "",
                    mount.value ?: "",
                    name.value ?: "",
                    saveService.value ?: false)

            this.name.value = ""
            this.alias.value = ""
            this.mount.value = ""

            Toast.makeText(view.context,"Procesando..." , Toast.LENGTH_LONG).show()
        } catch (e: Exception) {
            Log.e("ERROR", e.message ?: "")
        }
    }
}