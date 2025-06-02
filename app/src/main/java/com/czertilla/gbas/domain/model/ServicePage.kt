package com.czertilla.gbas.domain.model

import java.util.UUID

data class ServicePage(
    val id: UUID,
    val name: String,
    val price: Double,
    val description: String,
    val imageUrl: String,
    val author: LoggedInUser
)
