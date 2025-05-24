package com.czertilla.gbas.data

import android.content.Context
import android.util.Log
import androidx.credentials.CredentialManager
import androidx.credentials.GetCredentialRequest
import com.czertilla.gbas.R
import com.czertilla.gbas.data.auth.firebase.FirebaseAuthService
import com.czertilla.gbas.data.enum.OauthProvider
import com.czertilla.gbas.data.local.secure.SecureStorage
import com.czertilla.gbas.data.model.LoggedInUser
import com.czertilla.gbas.data.remote.api.FirebaseApi
import com.czertilla.gbas.data.remote.schema.FirebaseLoginRequest
import com.czertilla.gbas.data.user.UserStorage
import com.czertilla.gbas.ui.login.model.LoginResult
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.tasks.await

/**
 * Class that handles authentication w/ btn_login credentials and retrieves user information.
 */

class FirebaseAuthServiceImpl(
    private val context: Context,
    private val firebaseApi: FirebaseApi,
    private val secureStorage: SecureStorage,
    private val userRepository: UserStorage
) : FirebaseAuthService {

    private val auth = FirebaseAuth.getInstance()
    private val credentialManager = CredentialManager.create(context)

    override suspend fun getGoogleCredential(): GoogleIdTokenCredential? {
        val request = GetCredentialRequest.Builder()
            .addCredentialOption(
                GetGoogleIdOption.Builder()
                    .setFilterByAuthorizedAccounts(false)
                    .setServerClientId(context.getString(R.string.default_web_client_id))
                    .build()
            ).build()

        return try {
            val result = credentialManager.getCredential(context, request)
            result.credential as? GoogleIdTokenCredential
        } catch (e: Exception) {
            Log.e("FirebaseAuthServiceImpl.getGoogleCredential", "cant $e")
            null
        }
    }

    override suspend fun signInWithGoogleCredential(credential: GoogleIdTokenCredential): LoginResult {
        return try {
            val firebaseCredential = GoogleAuthProvider.getCredential(credential.idToken, null)
            val result = auth.signInWithCredential(firebaseCredential).await()
            val user = result.user ?: return LoginResult(error = "User is null")
            loginWithFirebaseToken(user)
        } catch (e: Exception) {
            LoginResult(error = e.message)
        }
    }

    private suspend fun loginWithFirebaseToken(firebaseUser: FirebaseUser): LoginResult {
        val tokenResult = firebaseUser.getIdToken(true).await()
        val token = tokenResult.token ?: return LoginResult(error = "Token retrieval failed")

        val response = firebaseApi.loginWithFirebase(FirebaseLoginRequest(token)) ?: return LoginResult(error = "Login failed")

        val loggedInUser = LoggedInUser(
            userId = response.userId,
            displayName = firebaseUser.displayName ?: "Unknown",
            email = firebaseUser.email,
            photoUrl = firebaseUser.photoUrl?.toString()
        )

        secureStorage.saveAccessToken(context, response.accessToken)
        userRepository.saveUserEntity(loggedInUser, OauthProvider.Google.name)

        return LoginResult(success = loggedInUser)
    }
}


