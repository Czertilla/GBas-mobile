package com.czertilla.gbas.domain.repository

import com.czertilla.gbas.domain.model.ServiceCard
import com.czertilla.gbas.domain.model.ServicePage
import java.util.UUID

interface ServiceRepository {
    suspend fun getLocalServices(): List<ServiceCard>
    suspend fun getRemoteServices(): List<ServiceCard>
    suspend fun getServices(forceRefresh: Boolean = false): List<ServiceCard>
    suspend fun getServicePage(serviceId: UUID): ServicePage?
}