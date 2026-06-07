package com.example.alyah_cat.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "perangkat_desa")
data class PerangkatEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val nama: String,
    val jabatan: String
)