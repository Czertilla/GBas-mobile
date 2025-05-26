package com.czertilla.gbas.data.local.db

import android.content.Context
import androidx.room.Room
import com.czertilla.gbas.data.local.dao.CookieDao
import com.czertilla.gbas.data.local.dao.OauthAccountDao
import com.czertilla.gbas.data.local.dao.ServiceDao
import com.czertilla.gbas.data.local.dao.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "gbas"
        ).build()
    }

    @Provides
    fun provideServiceDao(database: AppDatabase): ServiceDao {
        return database.serviceDao()
    }

    @Provides
    fun provideUserDao(database: AppDatabase): UserDao {
        return database.userDao()
    }

    @Provides
    fun provideCookieDao(database: AppDatabase): CookieDao {
        return database.cookieDao()
    }

    @Provides
    fun provideOauthAccountDao(database: AppDatabase): OauthAccountDao {
        return database.oauthAccountDao()
    }
}