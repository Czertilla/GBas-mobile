package com.czertilla.gbas.data

import android.content.Context
import com.czertilla.gbas.data.model.LoggedInUser
import com.czertilla.gbas.ui.login.LoggedInUserView
import kotlinx.coroutines.delay


class AuthRepository(private val context: Context) {

    private var user: LoggedInUser? = null

    val isLoggedIn: Boolean
        get() = user != null


    suspend fun login(username: String, password: String): LoggedInUserView {
            // Здесь может быть вызов API или работа с Room/SQLite
            delay(1000) // Имитация сетевого запроса

            return if (username.isNotBlank() && password.isNotBlank()) {
                LoggedInUserView("Test User", "1")
            } else {
                throw Exception("Invalid credentials")
            }
        }
    }