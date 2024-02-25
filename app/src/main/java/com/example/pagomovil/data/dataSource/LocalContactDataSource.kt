package com.example.pagomovil.data.dataSource

import androidx.lifecycle.LiveData
import com.example.pagomovil.models.Contact
import kotlinx.coroutines.flow.Flow

interface LocalContactDataSource {
    fun getAllContacts(): LiveData<List<Contact>>
    fun getContactById(id: Int): Contact
    fun insertContact(contact: Contact)
    fun deleteContact(contact: Contact)
}