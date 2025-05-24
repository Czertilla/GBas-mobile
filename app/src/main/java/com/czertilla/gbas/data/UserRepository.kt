package com.czertilla.gbas.data

import android.content.Context
import android.util.Log
import com.czertilla.gbas.data.enum.OauthProvider
import com.czertilla.gbas.data.local.db.AppDatabase
import com.czertilla.gbas.data.local.entity.OauthAccountEntity
import com.czertilla.gbas.data.local.entity.UserEntity
import com.czertilla.gbas.data.model.LoggedInUser
import java.util.UUID

class UserRepository(private val context: Context) {
    private val userDao = AppDatabase.getInstance(context).userDao()
    private val oauthDao = AppDatabase.getInstance(context).oauthAccountDao()

    suspend fun saveUserEntity(user: LoggedInUser, providerName: String?=null): Boolean {
        try {
            val userId = UUID.fromString(user.userId)
            userDao.insert(
                UserEntity(
                    id = userId,
                    displayName = user.displayName,
                    email = user.email,
                    photoUrl = user.photoUrl
                )
            )
            if (providerName != null && userId != null){
                return saveOauthEntity(userId, providerName)
            }
        } catch (e: IllegalArgumentException){
            Log.e("UserRepository.saveUserEntity", "bad User entity fields raised $e")
            return false
        }
        return true

    }

    private suspend fun saveOauthEntity(userId: UUID, providerName: String): Boolean {
        try {
            oauthDao.insert(OauthAccountEntity(
                userId =userId,
                provider = OauthProvider.valueOf(providerName)
            ))
        } catch (e: IllegalArgumentException) {
            Log.e("UserRepository.saveOauthEntity", "Illegal oauth providerName=$providerName raised $e")
            return false
        }
        return true
    }
}