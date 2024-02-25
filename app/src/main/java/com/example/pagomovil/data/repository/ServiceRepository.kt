package com.example.pagomovil.data.repository

import androidx.lifecycle.LiveData
import com.example.pagomovil.data.dataSource.LocalServiceDataSource
import com.example.pagomovil.models.Service

class ServiceRepository (private val localServiceDataSource: LocalServiceDataSource) {
    fun getAllServices(): LiveData<List<Service>> =
            localServiceDataSource.getAllServices()

    fun getServiceById(id: Int): Service =
            localServiceDataSource.getServiceById(id)

    fun saveService(service: Service) =
            localServiceDataSource.insertService(service)
}