package com.czertilla.gbas.ui.login

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.czertilla.gbas.R
import com.czertilla.gbas.data.auth.jwt.AuthRepositoryImpl
import com.czertilla.gbas.data.auth.jwt.AuthService
import com.czertilla.gbas.ui.login.model.LoginFormState
import com.czertilla.gbas.ui.login.model.LoginResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authService: AuthService
) : ViewModel() {
    private val _loginForm = MutableLiveData<LoginFormState>()
    val loginFormState: LiveData<LoginFormState> = _loginForm

    private val _loginResult = MutableLiveData<LoginResult>()
    val loginResult: LiveData<LoginResult> = _loginResult

    fun loginDataChanged(username: String, password: String) {
        _loginForm.value = when {
            !isUserNameValid(username) -> LoginFormState(usernameError = R.string.invalid_username)
            !isPasswordValid(password) -> LoginFormState(passwordError = R.string.invalid_password)
            else -> LoginFormState(isDataValid = true)
        }
    }

    fun login(username: String, password: String) {
        viewModelScope.launch {
            try {
                val result = authService.login(username, password)
                _loginResult.value = LoginResult(success = result)
            } catch (e: Exception) {
                _loginResult.value = LoginResult(error = R.string.login_failed.toString())
            }
        }
    }

    suspend fun loginWithGoogle(context: Context) {
        _loginResult.value = authService.loginWithGoogle(context)
    }

    private fun isUserNameValid(username: String) = username.isNotBlank() &&
            (username.contains('@') || username.matches(Regex("^\\+?[0-9]{10,15}$")))

    private fun isPasswordValid(password: String) = password.length > 5
}
