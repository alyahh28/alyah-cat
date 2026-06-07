package com.example.alyah_cat.Note

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.alyah_cat.data.AppDatabase
import com.example.alyah_cat.data.entity.NoteEntity
import com.example.alyah_cat.databinding.ActivityNoteFormBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NoteFormActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNoteFormBinding
    private val db by lazy { AppDatabase.getInstance(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        binding = ActivityNoteFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Setup Toolbar
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            title = "Tambah Catatan"
            setDisplayHomeAsUpEnabled(true)
        }
        binding.toolbar.setNavigationOnClickListener { finish() }

        binding.btnSaveNote.setOnClickListener {
            val title = binding.etTitle.text.toString().trim()
            val content = binding.etContent.text.toString().trim()

            if (title.isNotEmpty() && content.isNotEmpty()) {
                lifecycleScope.launch {
                    try {
                        val note = NoteEntity(
                            title = title,
                            content = content,
                            createdAt = System.currentTimeMillis()
                        )
                        // Pastikan operasi database dilakukan di IO Thread
                        withContext(Dispatchers.IO) {
                            db.noteDao().insertNote(note)
                        }
                        finish()
                    } catch (e: Exception) {
                        Toast.makeText(this@NoteFormActivity, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                Toast.makeText(this, "Isi semua kolom!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}