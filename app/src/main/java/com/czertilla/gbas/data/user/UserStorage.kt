package com.czertilla.gbas.data.user

import com.czertilla.gbas.domain.model.LoggedInUser

interface UserStorage {
    suspend fun saveUserEntity(user: LoggedInUser, providerName: String? = null): Boolean
    suspend fun getUserEntity(userId: String): LoggedInUser?
}