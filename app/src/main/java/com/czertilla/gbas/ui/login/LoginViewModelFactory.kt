package com.czertilla.gbas.ui.login

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.czertilla.gbas.data.AuthDataSource
import com.czertilla.gbas.data.AuthRepository

/**
 * ViewModel provider factory to instantiate LoginViewModel.
 * Required given LoginViewModel has a non-empty constructor
 */
class LoginViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(AuthRepository(AuthDataSource(context))) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}