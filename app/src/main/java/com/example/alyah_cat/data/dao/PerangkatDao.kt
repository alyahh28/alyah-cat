package com.example.alyah_cat.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.alyah_cat.data.entity.PerangkatEntity

@Dao
interface PerangkatDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPerangkat(perangkat: PerangkatEntity)

    @Query("SELECT * FROM perangkat_desa ORDER BY id DESC")
    suspend fun getAllPerangkat(): List<PerangkatEntity>

    @Delete
    suspend fun deletePerangkat(perangkat: PerangkatEntity) // <--- Pastikan tidak ada return value manual
}