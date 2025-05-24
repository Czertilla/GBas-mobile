package com.czertilla.gbas.ui.login

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.czertilla.gbas.data.FirebaseAuthServiceImpl
import com.czertilla.gbas.data.auth.jwt.AuthRepositoryImpl
import com.czertilla.gbas.data.auth.jwt.AuthService

/**
 * ViewModel provider factory to instantiate LoginViewModel.
 * Required given LoginViewModel has a non-empty constructor
 */
