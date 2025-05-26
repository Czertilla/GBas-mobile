package com.czertilla.gbas.data.local.datasource

import android.util.Log
import com.czertilla.gbas.data.local.dao.ServiceDao
import com.czertilla.gbas.data.local.entity.ServiceEntity
import javax.inject.Inject

class ServiceLocalDataSourceImpl @Inject constructor(
    private val serviceDao: ServiceDao
): ServiceLocalDataSource{
    override suspend fun getServices(): List<ServiceEntity> {
        return try {
            serviceDao.getAll()
        } catch (e: Exception) {
            Log.e("ServiceLocalDataSourceImpl.getServices", "$e")
            listOf<ServiceEntity>()
        }
    }

    override suspend fun saveServices(services: List<ServiceEntity>) {
        try {
            serviceDao.insertServices(services)
        } catch (e: Exception) {
            Log.e("ServiceLocalDataSourceImpl.saveServices", "$e")
        }
    }
}