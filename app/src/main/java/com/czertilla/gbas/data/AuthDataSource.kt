package com.czertilla.gbas.data

import android.content.Context
import androidx.credentials.CredentialManager
import androidx.credentials.GetCredentialRequest
import com.czertilla.gbas.R
import com.czertilla.gbas.data.local.secure.SecureStorage
import com.czertilla.gbas.data.model.LoggedInUser
import com.czertilla.gbas.data.remote.api.FirebaseApi
import com.czertilla.gbas.data.remote.client.RetrofitClient
import com.czertilla.gbas.data.remote.schema.FirebaseLoginRequest
import com.czertilla.gbas.ui.login.model.LoginResult
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.tasks.await
import java.io.IOException

/**
 * Class that handles authentication w/ btn_login credentials and retrieves user information.
 */
class AuthDataSource(private val context: Context) {
    private val auth = FirebaseAuth.getInstance()
    private val credentialManager = CredentialManager.create(context)
    private val firebaseApi = RetrofitClient.create(
        baseUrl = prefixRoute(R.string.route_auth_firebase),
        serviceClass = FirebaseApi::class.java
    )

    suspend fun getGoogleCredential(): GoogleIdTokenCredential? {
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
            null
        }
    }

    suspend fun firebaseAuthWithGoogle(credential: GoogleIdTokenCredential): LoginResult {
        return try {
            val googleIdToken = credential.idToken
            if (false) {
                return LoginResult(error = "Google ID Token is null")
            }

            val firebaseCredential = GoogleAuthProvider.getCredential(googleIdToken, null)
            val result = auth.signInWithCredential(firebaseCredential).await()
            val user = result.user

            if (user != null) {

                val syncResult = firebaseApi.loginWithFirebase(
                    token = FirebaseLoginRequest(
                        user.getIdToken(true).await().token.toString(),
                    )
                )
                val loggedInUser = LoggedInUser(
                    userId = syncResult?.userId ?: user.uid,
                    displayName = user.displayName ?: "Unknown",
                    email = user.email,
                    photoUrl = user.photoUrl?.toString()
                )
                if (syncResult != null){
                    SecureStorage.saveAccessToken(context, syncResult.accessToken)
                }
                LoginResult(success = loggedInUser)
            } else {
                LoginResult(error = "Пользователь не найден")
            }
        } catch (e: Exception) {
            LoginResult(error = e.message)
        }
    }

    fun login(username: String, password: String): Result<LoggedInUser> {
        try {
            // TODO: handle loggedInUser authentication
            val fakeUser = LoggedInUser(java.util.UUID.randomUUID().toString(), "Jane Doe")
            return Result.Success(fakeUser)
        } catch (e: Throwable) {
            return Result.Error(IOException("Error logging in", e))
        }
    }

    private fun prefixRoute(stringId: Int) : String =
        context.getString(R.string.api_base_url).toString() + context.getString(stringId).toString()

    fun logout() {
        // TODO: revoke authentication
    }
}