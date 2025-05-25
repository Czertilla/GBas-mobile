package com.czertilla.gbas

import android.content.Context
import com.czertilla.gbas.data.local.session.SessionManager
import com.czertilla.gbas.data.user.RoomUserStorage
import com.czertilla.gbas.data.user.UserStorage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object MainModule {

    @Provides
    fun provideSessionManager(@ApplicationContext context: Context): SessionManager {
        return SessionManager(context)
    }

}