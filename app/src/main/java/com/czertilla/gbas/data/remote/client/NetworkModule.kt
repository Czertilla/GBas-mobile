package com.czertilla.gbas.data.remote.client

import android.content.Context
import com.czertilla.gbas.R
import com.czertilla.gbas.data.local.db.AppDatabase
import com.czertilla.gbas.data.remote.api.ServiceApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.CookieJar
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(
        @ApplicationContext context: Context,
        cookieJar: CookieJar
    ): Retrofit {
        val baseUrl = context.getString(R.string.api_base_url)
        return mutableMapOf<String, Retrofit>().getOrPut(baseUrl) {
            val client = OkHttpClient.Builder()
                .cookieJar(cookieJar)
                .build()

            Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }

    @Provides
    fun provideCookieJar(
        @ApplicationContext context: Context
    ): CookieJar {
        return CookieJarImpl(AppDatabase.getInstance(context).cookieDao())
    }

    @Provides
    fun provideServiceApi(retrofit: Retrofit): ServiceApi {
        return retrofit.create(ServiceApi::class.java)
    }
}