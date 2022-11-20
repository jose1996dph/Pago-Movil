package com.example.pagomovil.data.dataSource

import androidx.lifecycle.LiveData
import com.example.pagomovil.models.Service

interface LocalServiceDataSource {
    fun getAllServices(): LiveData<List<Service>>
    fun getServiceById(id: Int): Service
    fun insertService(contact: Service)
}