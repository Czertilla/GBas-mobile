package com.czertilla.gbas.data.remote.api;

import com.czertilla.gbas.data.remote.schema.NewServicePageRequest
import com.czertilla.gbas.data.remote.schema.ServiceCardResponse
import com.czertilla.gbas.data.remote.schema.ServicePageResponse
import dagger.Provides


import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import java.util.UUID

interface ServiceApi {
    @GET("services/all/")
    suspend fun getAll(): List<ServiceCardResponse>?

    @GET("services/favorites/")
    suspend fun getFavorites(): List<ServiceCardResponse>?

    @GET("services/{service_id}/")
    suspend fun getPage(@Path("service_id") serviceId: UUID): ServicePageResponse?

    @POST("services/new/")
    suspend fun postNewPage(@Body newPage: NewServicePageRequest): ServiceCardResponse?
}