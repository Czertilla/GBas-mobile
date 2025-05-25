package com.czertilla.gbas.data.user

import android.content.Context
import android.util.Log
import com.czertilla.gbas.data.enum.OauthProvider
import com.czertilla.gbas.data.local.db.AppDatabase
import com.czertilla.gbas.data.local.entity.OauthAccountEntity
import com.czertilla.gbas.data.local.entity.UserEntity
import com.czertilla.gbas.data.model.LoggedInUser
import java.util.UUID

class RoomUserStorage(private val context: Context) : UserStorage {
    private val userDao = AppDatabase.getInstance(context).userDao()
    private val oauthDao = AppDatabase.getInstance(context).oauthAccountDao()

    override suspend fun saveUserEntity(user: LoggedInUser, providerName: String?): Boolean {
        return try {
            val userId = UUID.fromString(user.userId)
            userDao.insert(UserEntity(userId, user.displayName, user.email, user.photoUrl))
            if (providerName != null) {
                oauthDao.insert(
                    OauthAccountEntity(
                        userId = userId, provider = OauthProvider.valueOf(providerName)
                    )
                )
            }
            true
        } catch (e: IllegalArgumentException) {
            Log.e("UserRepository", "Error saving user: $e")
            false
        }
    }

    override suspend fun getUserEntity(userId: String): LoggedInUser? {
        return try {
            val userId = UUID.fromString(userId)
            val userEntity = userDao.getById(userId)
            if (userEntity != null){
                return LoggedInUser(
                    userId = userEntity.id.toString(),
                    displayName = userEntity.username,
                    email = userEntity.email,
                    photoUrl = userEntity.photoUrl
                )
            } else {
                null
            }

        } catch (e: IllegalArgumentException) {
            Log.e("UserRepository", "Error getting user by id=$userId: $e")
            null
        }
    }
}