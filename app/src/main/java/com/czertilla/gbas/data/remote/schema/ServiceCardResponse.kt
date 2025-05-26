package com.czertilla.gbas.data.remote.schema

import com.czertilla.gbas.data.local.entity.ServiceEntity
import com.czertilla.gbas.domain.model.ServiceCard
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.UUID

data class ServiceCardResponse (
    @SerializedName("id")
    @Expose
    val id: UUID,

    @SerializedName("name")
    @Expose
    val name: String,

    @SerializedName("author_id")
    @Expose
    val authorId: UUID,

    @SerializedName("photo_url")
    @Expose
    val imageUrl: String,

    @SerializedName("price")
    @Expose
    val price: Double
)
fun ServiceCardResponse.toEntity() = ServiceEntity(id, authorId, name, price, "", imageUrl)
fun ServiceCardResponse.toDomainCard() = ServiceCard(id, name, price, imageUrl)