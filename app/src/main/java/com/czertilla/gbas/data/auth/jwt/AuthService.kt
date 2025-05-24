package com.czertilla.gbas.data.auth.jwt

import com.czertilla.gbas.data.model.LoggedInUser
import com.czertilla.gbas.ui.login.model.LoginResult

interface AuthService {
    suspend fun login(username: String, password: String): LoggedInUser
    suspend fun loginWithGoogle(): LoginResult
}