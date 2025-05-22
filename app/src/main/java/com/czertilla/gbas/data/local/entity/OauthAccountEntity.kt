package com.czertilla.gbas.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.czertilla.gbas.data.enum.OauthProvider


@Entity(
    tableName = "oauth_account",
    foreignKeys = [ForeignKey(
        entity = UserEntity::class,
        parentColumns = ["id"],
        childColumns = ["userId"],
        onDelete = ForeignKey.CASCADE
    )],
    indices = [Index("userId")]
)
data class OauthAccountEntity(
    @PrimaryKey val provider: OauthProvider,
    @PrimaryKey val id: String,
    val userId: String,
)