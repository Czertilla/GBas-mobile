package com.czertilla.gbas.data.remote.api

import com.czertilla.gbas.data.model.LoginRequest
import com.czertilla.gbas.data.remote.schema.UserCreate
import com.czertilla.gbas.data.remote.schema.UserRead
import com.czertilla.gbas.data.remote.schema.VerifyRequest
import com.czertilla.gbas.data.remote.schema.VerifyTokenRequest
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    @POST("login/")
    suspend fun login(@Body body: LoginRequest): UserRead

    @POST("register/")
    fun register(
        @Body userCreate: UserCreate?
    ): Call<UserCreate?>?

    @POST("request-verify-token/")
    fun requestToken(
        @Body verifyTokenRequest: VerifyTokenRequest?
    ): Call<Any?>?

    @POST("verify/")
    fun verify(
        @Body verifyRequest: VerifyRequest?
    ): Call<Any?>?

    @POST("jwt/logout/")
    fun logout(): Call<Any?>?
}