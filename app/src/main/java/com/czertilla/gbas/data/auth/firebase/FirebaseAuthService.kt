package com.czertilla.gbas.data.auth.firebase

import com.czertilla.gbas.ui.login.model.LoginResult
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential

interface FirebaseAuthService {
    suspend fun getGoogleCredential(): GoogleIdTokenCredential?
    suspend fun signInWithGoogleCredential(credential: GoogleIdTokenCredential): LoginResult
}