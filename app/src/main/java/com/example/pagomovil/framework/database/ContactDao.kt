package com.example.pagomovil.framework.database

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*
import com.example.pagomovil.models.Contact
import kotlinx.coroutines.flow.Flow

@Dao
interface ContactDao {

    @Query("SELECT * FROM Contact ORDER BY name")
    fun getAllContacts(): LiveData<List<Contact>>

    @Query("SELECT * FROM Contact ORDER BY name")
    fun getAllContactsList(): List<Contact>

    @Query("SELECT * FROM Contact WHERE id = :id")
    fun getContactById(id: Int): Contact

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertContact(contactEntity: Contact)

    @Delete
    fun deleteContact(contactEntity: Contact)
}