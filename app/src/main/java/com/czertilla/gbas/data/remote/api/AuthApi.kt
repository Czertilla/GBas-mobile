package com.czertilla.gbas.data.remote.api

import com.czertilla.gbas.domain.model.LoginRequest
import com.czertilla.gbas.data.remote.schema.UserCreate
import com.czertilla.gbas.data.remote.schema.UserRead
import com.czertilla.gbas.data.remote.schema.VerifyRequest
import com.czertilla.gbas.data.remote.schema.VerifyTokenRequest
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    @POST("login/")
    suspend fun login(@Body body: LoginRequest): UserRead?

    @POST("register/")
    suspend fun register(
        @Body userCreate: UserCreate?
    ): UserCreate?

    @POST("request-verify-token/")
    suspend fun requestToken(
        @Body verifyTokenRequest: VerifyTokenRequest?
    ): Any?

    @POST("verify/")
    suspend fun verify(
        @Body verifyRequest: VerifyRequest?
    ): Any?

    @POST("jwt/logout/")
    suspend fun logout(): Any?
}