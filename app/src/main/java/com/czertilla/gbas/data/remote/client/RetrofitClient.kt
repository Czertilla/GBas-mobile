package com.czertilla.gbas.data.remote.client


import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private val retrofitInstances = mutableMapOf<String, Retrofit>()
    private val cookieJar = AuthCookieJar()

    fun <T> create(
        baseUrl: String,
        serviceClass: Class<T>
    ): T {
        val retrofit = retrofitInstances.getOrPut(baseUrl) {
            val client = OkHttpClient.Builder()
                .cookieJar(cookieJar)
                .addInterceptor { chain ->
                    val requestBuilder = chain.request().newBuilder()
                    val token = cookieJar.getAuthToken(chain.request().url.host)
                    token?.let {
                        requestBuilder.addHeader("Authorization", "Bearer $it")
                    }
                    chain.proceed(requestBuilder.build())
                }
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

