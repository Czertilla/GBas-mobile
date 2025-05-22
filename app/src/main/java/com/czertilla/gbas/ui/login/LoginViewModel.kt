package com.czertilla.gbas.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.czertilla.gbas.R
import com.czertilla.gbas.data.AuthRepository
import kotlinx.coroutines.launch

class LoginViewModel(private val repository: AuthRepository) : ViewModel() {
    private val _loginForm = MutableLiveData<LoginFormState>()
    val loginFormState: LiveData<LoginFormState> = _loginForm

    private val _loginResult = MutableLiveData<LoginResult>()
    val loginResult: LiveData<LoginResult> = _loginResult

    fun loginDataChanged(username: String, password: String) {
        if (!isUserNameValid(username)) {
            _loginForm.value = LoginFormState(usernameError = R.string.invalid_username)
        } else if (!isPasswordValid(password)) {
            _loginForm.value = LoginFormState(passwordError = R.string.invalid_password)
        } else {
            _loginForm.value = LoginFormState(isDataValid = true)
        }
    }

    fun login(username: String, password: String) {
        viewModelScope.launch {
            try {
                val result = repository.login(username, password)
                _loginResult.value = LoginResult(success = result)
            } catch (e: Exception) {
                _loginResult.value = LoginResult(error = R.string.login_failed)
            }
        }
    }

    private fun isUserNameValid(username: String): Boolean {
        return username.isNotBlank()
                && (username.contains('@') || username.matches(Regex("^\\+?[0-9]{10,15}$")))
    }

    private fun isPasswordValid(password: String): Boolean {
        return password.length > 5
    }
}
