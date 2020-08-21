package com.example.pagomovil.viewModels

import android.util.Log
import android.view.View
import android.widget.Button
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pagomovil.models.Bank
import com.example.pagomovil.models.BanksNumber
import com.example.pagomovil.services.Message


class PayViewModel : ViewModel() {

    var dni: MutableLiveData<String> =  MutableLiveData<String>()
    var phone: MutableLiveData<String> =  MutableLiveData<String>()
    var mount: MutableLiveData<String> =  MutableLiveData<String>()
    var operatorPosition: MutableLiveData<Int> =  MutableLiveData<Int>()
    var bankPosition: MutableLiveData<Int> = MutableLiveData<Int>()

    val banks: MutableLiveData<List<Bank>> = MutableLiveData<List<Bank>>()
    val operators: MutableLiveData<List<String>> = MutableLiveData<List<String>>()

    init {
        banks.value = ArrayList<Bank>().apply {
            add(Bank("100% Banco", "0156"))
            add(Bank("Bancamiga", "0172"))
            add(Bank("Bancaribe", "0114"))
            add(Bank("Banco Agrícola de Venezuela", "0166"))
            add(Bank("Banco Caroní", "0128"))
            add(Bank("Banco de Venezuela, S.A.C.A.", "0102"))
            add(Bank("Banco del Tesoro", "0163"))
            add(Bank("Banco Plaza", "0138"))
            add(Bank("Bancrecer", "0168"))
            add(Bank("Banesco", "0134"))
            add(Bank("Banfanb", "0177"))
            add(Bank("Banplus", "0174"))
            add(Bank("BFC Banco Fondo Común", "0151"))
            add(Bank("Bicentenario del Pueblo", "0175"))
            add(Bank("BNC Nacional de Crédito", "0191"))
            add(Bank("Del Sur", "0157"))
            add(Bank("Exterior", "0115"))
            add(Bank("Mercantil", "0105"))
            add(Bank("Mi Banco", "0169"))
            add(Bank("Occidental de Descuento", "0116"))
            add(Bank("Provincial", "0108"))
            add(Bank("Venezolano de Crédito", "0104"))
        }
        operators.value = ArrayList<String>().apply {
            add("0412")
            add("0414")
            add("0424")
            add("0416")
            add("0426")
        }
    }

    fun getBank(position: Int): Bank{
        return banks.value?.get(position) ?: Bank("","")
    }

    fun getBanksName(): List<String>{
        return banks.value?.map { it.name } ?: emptyList<String>()
    }

    fun doPay(v: View) {

        val pay: Button = v as Button
        pay.isEnabled = false

        val number: String = banks.value?.get(bankPosition.value ?: 0)?.number ?: ""
        val ope: String =  operators.value?.get(operatorPosition.value ?: 0) ?: ""
        if (number == "") {
            pay.isEnabled = true
            return
        }
        if (ope == "") {
            pay.isEnabled = true
            return
        }
        if (phone.value == null || phone.value?.isEmpty() == true) {
            pay.isEnabled = true
            return
        }
        if (dni.value == null || dni.value?.isEmpty() == true) {
            pay.isEnabled = true
            return
        }
        if (this.mount.value == null || this.mount.value?.isEmpty() == true) {
            pay.isEnabled = true
            return
        }
        // Code here
        // Code here
        var mount: String = this.mount.value.toString()
        mount = editMount(mount)

        val text = "Pagar $number $ope${phone.value} ${dni.value} $mount"

        try {
            val message = Message(text, BanksNumber.Venezuela)
            message.send()
            this.phone.value = ""
            this.dni.value = ""
            this.mount.value = ""
        } catch (e: Exception) {
            Log.e("ERROR", e.message ?: "")
        } finally {
            pay.isEnabled = true
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

