package com.czertilla.gbas.data.local.datasource

import com.czertilla.gbas.data.local.entity.ServiceEntity

interface ServiceLocalDataSource {
    suspend fun getServices(): List<ServiceEntity>
    suspend fun saveServices(services: List<ServiceEntity>)
}