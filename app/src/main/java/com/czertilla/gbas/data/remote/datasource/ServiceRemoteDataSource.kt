package com.czertilla.gbas.data.remote.datasource

import com.czertilla.gbas.data.remote.schema.ServiceCardResponse


interface ServiceRemoteDataSource {
    suspend fun fetchServices(): List<ServiceCardResponse>
}