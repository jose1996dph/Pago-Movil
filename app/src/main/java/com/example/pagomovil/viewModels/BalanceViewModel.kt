package com.example.pagomovil.viewModels

import android.telephony.SmsManager
import android.view.View
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pagomovil.models.BanksNumber
import com.example.pagomovil.services.Message

class BalanceViewModel : ViewModel() {
    
    fun consultCSC(view: View) {
        sendMessage(view, "CSC")
    }
    fun consultCST(view: View) {
        sendMessage(view, "CST")
    }
    fun consultCMC(view: View) {
        sendMessage(view, "CMC")
    }
    fun consultCMT(view: View) {
        sendMessage(view, "CMT")
    }

    private fun sendMessage(view: View, text: String) {
        try {
            val message = Message(text, BanksNumber.Venezuela)
            message.send()
            showToast(view, "Procesando...")
        } catch (e: Exception) {
            showToast(view, e.message)
        }
    }

    private fun showToast(view: View?, text: String?) {
        if (view != null)
            Toast.makeText(view?.getContext(), text, Toast.LENGTH_LONG).show()
    }

}