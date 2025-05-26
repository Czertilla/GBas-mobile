package com.czertilla.gbas.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.czertilla.gbas.data.local.entity.ServiceEntity
import com.czertilla.gbas.data.local.entity.UserEntity
import java.util.UUID

@Dao
interface ServiceDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(service: ServiceEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertServices(services: List<ServiceEntity>)

    @Query("SELECT * FROM service WHERE id = :id")
    suspend fun getById(id: UUID): ServiceEntity?

    @Query("SELECT * FROM service")
    suspend fun getAll(): List<ServiceEntity>
}