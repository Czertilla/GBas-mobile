package com.czertilla.gbas.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.czertilla.gbas.data.enum.OauthProvider
import com.czertilla.gbas.data.local.entity.OauthAccountEntity

@Dao
interface OauthAccountDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(account: OauthAccountEntity)

    @Query("SELECT * FROM oauth_account WHERE id = :id AND provider = :provider")
    suspend fun getByProviderId(provider: OauthProvider, id: String): OauthAccountEntity?
}