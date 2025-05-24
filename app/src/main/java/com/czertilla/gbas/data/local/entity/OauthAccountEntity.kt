package com.czertilla.gbas.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import com.czertilla.gbas.data.enum.OauthProvider
import java.util.UUID


@Entity(
    tableName = "oauth_account",
    foreignKeys = [ForeignKey(
        entity = UserEntity::class,
        parentColumns = ["id"],
        childColumns = ["userId"],
        onDelete = ForeignKey.CASCADE
    )],
    primaryKeys = ["provider", "userId"],
    indices = [Index("userId")]
)
data class OauthAccountEntity(
    val userId: UUID,
    val provider: OauthProvider,
)