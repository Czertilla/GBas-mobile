package com.czertilla.gbas.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "user")
data class UserEntity(
    @PrimaryKey val id: UUID,
    val username: String,
    val email: String?,
    val photoUrl: String?
)