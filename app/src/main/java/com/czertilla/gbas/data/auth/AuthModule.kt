package com.czertilla.gbas.data.auth

import android.content.Context
import com.czertilla.gbas.R
import com.czertilla.gbas.data.FirebaseAuthServiceImpl
import com.czertilla.gbas.data.auth.firebase.FirebaseAuthService
import com.czertilla.gbas.data.auth.jwt.AuthRepositoryImpl
import com.czertilla.gbas.data.auth.jwt.AuthService
import com.czertilla.gbas.data.local.secure.SecureStorage
import com.czertilla.gbas.data.remote.api.FirebaseApi
import com.czertilla.gbas.data.remote.client.ApiFactory
import com.czertilla.gbas.data.user.RoomUserStorage
import com.czertilla.gbas.data.user.UserStorage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthModule {

    @Provides
    fun provideSecureStorage(): SecureStorage = SecureStorage

    @Provides
    @Singleton
    fun provideBaseUrl(@ApplicationContext context: Context): String {
        return context.getString(R.string.api_base_url) +
                context.getString(R.string.route_auth_firebase)
    }

    @Provides
    @Singleton
    fun provideFirebaseApi(baseUrl: String): FirebaseApi {
        return ApiFactory.create(baseUrl, FirebaseApi::class.java)
    }

    @Provides
    fun provideUserStorage(@ApplicationContext context: Context): UserStorage {
        return RoomUserStorage(context)
    }

    @Provides
    fun provideFirebaseAuthService(
        @ApplicationContext context: Context,
        firebaseApi: FirebaseApi,
        secureStorage: SecureStorage,
        userStorage: UserStorage
    ): FirebaseAuthService {
        return FirebaseAuthServiceImpl(context, firebaseApi, secureStorage, userStorage)
    }

    @Provides
    fun provideAuthService(firebaseAuthService: FirebaseAuthService): AuthService {
        return AuthRepositoryImpl(firebaseAuthService)
    }
}