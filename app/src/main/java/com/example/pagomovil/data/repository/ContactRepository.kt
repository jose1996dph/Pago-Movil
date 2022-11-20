package com.example.pagomovil.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.pagomovil.data.dataSource.LocalContactDataSource
import com.example.pagomovil.models.Contact
import kotlinx.coroutines.flow.Flow

class ContactRepository (private val localContactDataSource: LocalContactDataSource) {

    fun getAllContacts(): LiveData<List<Contact>> =
        localContactDataSource.getAllContacts()

    fun getContactById(id: Int): Contact =
        localContactDataSource.getContactById(id)

    fun saveContact(contact: Contact) =
        localContactDataSource.insertContact(contact)

    fun deleteContact(contact: Contact) =
        localContactDataSource.deleteContact(contact)
}
