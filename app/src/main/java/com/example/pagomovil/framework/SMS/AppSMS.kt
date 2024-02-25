package com.example.pagomovil.framework.SMS

import android.telephony.SmsManager
import com.example.pagomovil.models.BanksNumber

class AppSMS() {
    private val sms: SmsManager by lazy { SmsManager.getDefault() }

    public fun sendMessage(body: String){
        sms.sendTextMessage(
                BanksNumber.Venezuela,
                null, body,
                null,
                null
        )
    }
}