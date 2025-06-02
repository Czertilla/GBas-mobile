package com.czertilla.gbas.data.remote.datasource

import android.util.Log
import com.czertilla.gbas.data.remote.api.ServiceApi
import com.czertilla.gbas.data.remote.schema.ServiceCardResponse
import com.czertilla.gbas.data.remote.schema.ServicePageResponse
import java.util.UUID
import javax.inject.Inject

class ServiceRemoteDataSourceImpl @Inject constructor(
    private val api: ServiceApi
) : ServiceRemoteDataSource {

    override suspend fun fetchServices(): List<ServiceCardResponse> {
        return try {
            val response = api.getAll() ?: return listOf<ServiceCardResponse>()
            response
        }catch (e: Exception) {
            Log.e("ServiceRemoteDataSourceImpl.fetchServices", "$e")
            listOf<ServiceCardResponse>()
        }

    }

    override suspend fun fetchServicePage(serviceId: UUID): ServicePageResponse? {
        return try {
            val result = api.getPage(serviceId)
            result
        }catch (e: Exception) {
            Log.e("ServiceRemoteDataSourceImpl.fetchServicePage", "$e")
            null
        }

    }
}