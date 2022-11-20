package com.example.pagomovil.framework.database

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataScope
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.example.pagomovil.data.dataSource.LocalContactDataSource
import com.example.pagomovil.models.Contact
import kotlinx.coroutines.flow.Flow
import java.util.concurrent.Callable
import java.util.concurrent.ExecutionException
import java.util.concurrent.Executors

class DatabaseContactDataSource(
    database: AppDatabase
): LocalContactDataSource {

    private val contactDao: ContactDao by lazy { database.contactDao() }

    val mContact = MediatorLiveData<List<Contact>>()

    @Throws(ExecutionException::class, InterruptedException::class)
    override fun getAllContacts(): LiveData<List<Contact>> {
        val callable = Callable {contactDao.getAllContactsList()}

        val future = Executors.newSingleThreadExecutor().submit(callable)

        val list = future!!.get()

        return contactDao.getAllContacts()
    }

    @Throws(ExecutionException::class, InterruptedException::class)
    override fun getContactById(id: Int): Contact {
        val callable = Callable { contactDao.getContactById(id) }

        val future = Executors.newSingleThreadExecutor().submit(callable)

        return future!!.get()
    }

    @Throws(ExecutionException::class, InterruptedException::class)
    override fun insertContact(contact: Contact) {
        val callable = Callable {contactDao.insertContact(contact)}

        val future = Executors.newSingleThreadExecutor().submit(callable)

        return future!!.get()
    }

    override fun deleteContact(contact: Contact) {
        val callable = Callable {contactDao.deleteContact(contact)}

        val future = Executors.newSingleThreadExecutor().submit(callable)

        return future!!.get()
    }
}