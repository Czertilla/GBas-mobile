package com.czertilla.gbas.data.remote.datasource

import android.util.Log
import com.czertilla.gbas.data.remote.api.ServiceApi
import com.czertilla.gbas.data.remote.schema.ServiceCardResponse
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
}