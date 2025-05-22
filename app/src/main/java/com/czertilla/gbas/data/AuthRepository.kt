package com.czertilla.gbas.data

import com.czertilla.gbas.data.model.LoggedInUser
import com.czertilla.gbas.ui.login.model.LoginResult
import kotlinx.coroutines.delay


class AuthRepository(private val dataSource: AuthDataSource) {

    private var user: LoggedInUser? = null

    val isLoggedIn: Boolean
        get() = user != null

    suspend fun signInWithGoogle(): LoginResult {
        val credential = dataSource.getGoogleCredential()
            ?: return LoginResult(success = null, error = "Не удалось получить credential")
        return dataSource.firebaseAuthWithGoogle(credential)
    }

    suspend fun login(username: String, password: String): LoggedInUser {
            // Здесь может быть вызов API или работа с Room/SQLite
            delay(1000) // Имитация сетевого запроса

            return if (username.isNotBlank() && password.isNotBlank()) {
                LoggedInUser("Test User", "1")
            } else {
                throw Exception("Invalid credentials")
            }
        }
    }

