package com.example.alyah_cat.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.alyah_cat.data.entity.NoteEntity

@Dao
interface NoteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: NoteEntity)

    @Query("SELECT * FROM notes ORDER BY id DESC")
    suspend fun getAllNotes(): List<NoteEntity>

    @Delete
    suspend fun deleteNote(note: NoteEntity)
}