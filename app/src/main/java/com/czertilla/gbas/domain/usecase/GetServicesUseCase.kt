package com.czertilla.gbas.domain.usecase

import com.czertilla.gbas.domain.model.ServiceCard
import com.czertilla.gbas.domain.repository.ServiceRepository
import javax.inject.Inject


class GetServicesUseCase @Inject constructor(
    private val repository: ServiceRepository
) {
    suspend operator fun invoke(forceRefresh: Boolean = false): List<ServiceCard> =
        repository.getServices(forceRefresh)
}