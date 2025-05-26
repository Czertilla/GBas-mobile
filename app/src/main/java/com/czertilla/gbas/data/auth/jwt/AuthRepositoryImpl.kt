package com.czertilla.gbas.data.auth.jwt

import android.content.Context
import com.czertilla.gbas.data.auth.firebase.FirebaseAuthService
import com.czertilla.gbas.domain.model.LoggedInUser
import com.czertilla.gbas.ui.login.model.LoginResult
import kotlinx.coroutines.delay


class AuthRepositoryImpl(
private val firebaseAuthService: FirebaseAuthService
) : AuthService {

    private var loggedInUser: LoggedInUser? = null

    override suspend fun login(username: String, password: String): LoggedInUser {
        delay(1000) // Мокаем реальный вызов
        return if (username.isNotBlank() && password.isNotBlank()) {
            LoggedInUser("Test User", "1")
        } else throw Exception("Invalid credentials")
    }

    override suspend fun loginWithGoogle(context: Context): LoginResult {
        val credential = firebaseAuthService.getGoogleCredential(context)
            ?: return LoginResult(error = "Не удалось получить credential")

        val result = firebaseAuthService.signInWithGoogleCredential(credential)
        if (result.success != null) {
            loggedInUser = result.success
        }
        return result
    }
}