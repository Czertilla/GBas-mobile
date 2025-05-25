package com.czertilla.gbas.data.auth.firebase

import android.content.Context
import com.czertilla.gbas.ui.login.model.LoginResult
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential

interface FirebaseAuthService {
    suspend fun getGoogleCredential(context: Context): GoogleIdTokenCredential?
    suspend fun signInWithGoogleCredential(credential: GoogleIdTokenCredential): LoginResult
}