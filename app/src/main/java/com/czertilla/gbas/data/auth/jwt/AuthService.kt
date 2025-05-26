package com.czertilla.gbas.data.auth.jwt

import android.content.Context
import com.czertilla.gbas.domain.model.LoggedInUser
import com.czertilla.gbas.ui.login.model.LoginResult

interface AuthService {
    suspend fun login(username: String, password: String): LoggedInUser
    suspend fun loginWithGoogle(context: Context): LoginResult
}