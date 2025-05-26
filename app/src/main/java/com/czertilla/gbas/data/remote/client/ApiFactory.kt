package com.czertilla.gbas.data.remote.client


import okhttp3.CookieJar
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class ApiFactory @Inject constructor(
    private val cookieJar: CookieJar
) {

    fun <T> create(
        baseUrl: String,
        serviceClass: Class<T>
    ): T {
        val retrofit = mutableMapOf<String, Retrofit>().getOrPut(baseUrl) {
            val client = OkHttpClient.Builder()
                .cookieJar(cookieJar)
                .build()

            Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        return retrofit.create(serviceClass)
    }
}

