package com.czertilla.gbas.data.remote.api

import com.czertilla.gbas.data.remote.schema.FirebaseLoginRequest
import com.czertilla.gbas.data.remote.schema.FirebaseLoginResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface FirebaseApi {
    @POST("login/")
    suspend fun loginWithFirebase(@Body token: FirebaseLoginRequest): FirebaseLoginResponse?

}