package com.czertilla.gbas.domain.model

import java.util.UUID

data class ServiceCard(
    val id: UUID,
    val name: String,
    val pricePerDay: Double,
    val imageUrl: String?
)