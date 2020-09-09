package com.example.pagomovil.viewModels

import android.util.Log
import android.view.View
import android.widget.Button
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pagomovil.models.Bank
import com.example.pagomovil.models.BanksNumber
import com.example.pagomovil.services.Message

class ServicesViewModel : ViewModel() {
    var alias: MutableLiveData<String> =  MutableLiveData<String>()
    var mount: MutableLiveData<String> =  MutableLiveData<String>()
    var servicePosition: MutableLiveData<Int> = MutableLiveData<Int>()

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

    fun doPay(v: View){
        val button: Button = v as Button
        button.isEnabled = false

        this.alias.postValue("")

        val service: String? =  services.value?.get(servicePosition.value ?: 0)
        if (service.isNullOrEmpty()) {
            button.isEnabled = true
            return
        }

        if (alias.value.isNullOrEmpty()) {
            button.isEnabled = true
            return
        }
        if (this.mount.value.isNullOrEmpty()) {
            button.isEnabled = true
            return
        }
        // Code here
        var mount: String = this.mount.value.toString()
        mount = editMount(mount)

        val text = "Servicio $service ${alias.value} $mount"

        try {
            val message = Message(text, BanksNumber.Venezuela)
            message.send()
            this.alias.postValue("")
            this.mount.postValue("")
            this.mount.value =  ""
        } catch (e: Exception) {
            Log.e("ERROR", e.message ?: "")
        } finally {
            button.isEnabled = true
        }
    }

    private fun editMount(mount: String): String {
        var mount = mount
        mount = mount.replace(",", "")
        mount = mount.replace(".", ",")
        if (mount.contains(",")) {
            val mounts = mount.split(",".toRegex()).toTypedArray()
            val decimal = mounts[mounts.size - 1]
            mount = mounts[0] + ","
            for (i in 0..1) {
                mount += if (decimal.length > i) decimal[i] else "0"
            }
        } else {
            mount += ",00"
        }
        return mount
    }
}