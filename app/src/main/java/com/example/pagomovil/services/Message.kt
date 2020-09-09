package com.example.pagomovil.services

import android.telephony.SmsManager
import android.util.Log
import com.example.pagomovil.models.BanksNumber


class Message (val body: String, val number: String) {
    fun send(){

        try {
            val sms: SmsManager = SmsManager.getDefault()
            sms.sendTextMessage(number, null, body, null, null)
        } catch (e: Exception) {
            Log.e("ERROR", e.message ?: "")
        }
    }
}