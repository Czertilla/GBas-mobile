package com.czertilla.gbas.data.user

import android.content.Context
import android.util.Log
import com.czertilla.gbas.data.enum.OauthProvider
import com.czertilla.gbas.data.local.dao.OauthAccountDao
import com.czertilla.gbas.data.local.dao.UserDao
import com.czertilla.gbas.data.local.db.AppDatabase
import com.czertilla.gbas.data.local.entity.OauthAccountEntity
import com.czertilla.gbas.data.local.entity.UserEntity
import com.czertilla.gbas.domain.model.LoggedInUser
import java.util.UUID
import javax.inject.Inject

class RoomUserStorage @Inject constructor(
    private val userDao: UserDao,
    private val oauthDao: OauthAccountDao
) : UserStorage {


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