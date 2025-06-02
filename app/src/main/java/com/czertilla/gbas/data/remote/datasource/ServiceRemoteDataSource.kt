package com.czertilla.gbas.data.remote.datasource

import com.czertilla.gbas.data.remote.schema.ServiceCardResponse
import com.czertilla.gbas.data.remote.schema.ServicePageResponse
import java.util.UUID


interface ServiceRemoteDataSource {
    suspend fun fetchServices(): List<ServiceCardResponse>
    suspend fun fetchServicePage(serviceId: UUID): ServicePageResponse?
}