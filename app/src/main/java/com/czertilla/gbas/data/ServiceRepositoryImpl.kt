package com.czertilla.gbas.data

import com.czertilla.gbas.data.local.datasource.ServiceLocalDataSource
import com.czertilla.gbas.data.local.entity.toDomainCard
import com.czertilla.gbas.data.remote.datasource.ServiceRemoteDataSource
import com.czertilla.gbas.data.remote.schema.toDomainCard
import com.czertilla.gbas.data.remote.schema.toEntity
import com.czertilla.gbas.domain.model.ServiceCard
import com.czertilla.gbas.domain.repository.ServiceRepository
import javax.inject.Inject

class ServiceRepositoryImpl @Inject constructor(
    private val local: ServiceLocalDataSource,
    private val remote: ServiceRemoteDataSource
) : ServiceRepository {

    override suspend fun getLocalServices(): List<ServiceCard> {
        return local.getServices().map { it.toDomainCard() }
    }

    override suspend fun getRemoteServices(): List<ServiceCard> {
        val remoteData = remote.fetchServices()
        local.saveServices(remoteData.map { it.toEntity() })
        return remoteData.map { it.toDomainCard() }
    }

    override suspend fun getServices(forceRefresh: Boolean): List<ServiceCard> {
        return if (forceRefresh) {
            getRemoteServices()
        } else {
            val cached = local.getServices()
            if (cached.isNotEmpty()) cached.map { it.toDomainCard() }
            else getRemoteServices()
        }
    }
}
