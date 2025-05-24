package com.czertilla.gbas.data.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.czertilla.gbas.data.local.dao.OauthAccountDao
import com.czertilla.gbas.data.local.dao.UserDao
import com.czertilla.gbas.data.local.entity.OauthAccountEntity
import com.czertilla.gbas.data.local.entity.UserEntity

@Database(
    entities = [UserEntity::class, OauthAccountEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun oauthAccountDao(): OauthAccountDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase{
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "gbas"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}