package com.example.pagomovil.framework.database

import androidx.lifecycle.LiveData
import com.example.pagomovil.data.dataSource.LocalServiceDataSource
import com.example.pagomovil.models.Service

class DatabaseServiceDataSource (
        database: AppDatabase
): LocalServiceDataSource {
    override fun getAllServices(): LiveData<List<Service>> {
        TODO("Not yet implemented")
    }

    override fun getServiceById(id: Int): Service {
        TODO("Not yet implemented")
    }

    override fun insertService(contact: Service) {
        TODO("Not yet implemented")
    }
}