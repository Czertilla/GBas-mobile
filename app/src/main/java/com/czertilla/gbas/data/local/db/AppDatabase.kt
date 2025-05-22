package com.czertilla.gbas.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
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
}