package com.example.alyah_cat.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.alyah_cat.data.dao.NoteDao
import com.example.alyah_cat.data.dao.PerangkatDao
import com.example.alyah_cat.data.entity.NoteEntity
import com.example.alyah_cat.data.entity.PerangkatEntity

@Database(
    entities = [NoteEntity::class, PerangkatEntity::class], // 2 Menu Database Terdaftar
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun noteDao(): NoteDao
    abstract fun perangkatDao(): PerangkatDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                )
                    .fallbackToDestructiveMigration() // Penyelamat error skema
                    .build().also { INSTANCE = it }
            }
        }
    }
}