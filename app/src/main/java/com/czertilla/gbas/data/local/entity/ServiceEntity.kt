package com.czertilla.gbas.data.local.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.czertilla.gbas.domain.model.ServiceCard
import java.util.UUID

@Entity(
    tableName = "service",
    indices = [Index("authorId")]
)
data class ServiceEntity(
    @PrimaryKey val id: UUID,
    val authorId: UUID,
    val name: String,
    val price: Double,
    val description: String,
    val imageUrl: String?
)


fun ServiceEntity.toDomainCard() = ServiceCard(id, name, price, imageUrl)

