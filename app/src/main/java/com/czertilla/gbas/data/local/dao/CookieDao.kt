package com.czertilla.gbas.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.czertilla.gbas.data.local.entity.CookieEntity

@Dao
interface CookieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(cookies: List<CookieEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(cookie: CookieEntity)

    @Query("SELECT * FROM cookie WHERE domain = :domain")
    fun getCookies(domain: String): List<CookieEntity>
}