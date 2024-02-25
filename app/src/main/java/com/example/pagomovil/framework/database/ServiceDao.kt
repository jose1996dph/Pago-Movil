package com.example.pagomovil.framework.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.pagomovil.models.Service

@Dao
interface ServiceDao {
    @Query("SELECT * FROM Service")
    fun getAllServices(): LiveData<List<Service>>

    @Query("SELECT * FROM Service WHERE id = :id")
    fun getServiceById(id: Int): Service

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertService(characterEntity: Service)

    @Delete
    fun deleteService(characterEntity: Service)
}