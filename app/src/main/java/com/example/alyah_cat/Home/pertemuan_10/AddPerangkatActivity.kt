package com.example.alyah_cat.Home.pertemuan_10

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.alyah_cat.data.AppDatabase
import com.example.alyah_cat.data.entity.PerangkatEntity
import com.example.alyah_cat.databinding.ActivityAddPerangkatBinding
import kotlinx.coroutines.launch

class AddPerangkatActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddPerangkatBinding
    private lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddPerangkatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = AppDatabase.getInstance(this)

        binding.btnSavePerangkat.setOnClickListener {
            val nama = binding.etNamaPerangkat.text.toString()
            val jabatan = binding.etJabatanPerangkat.text.toString()

            if (nama.isNotBlank() && jabatan.isNotBlank()) {
                lifecycleScope.launch {
                    val perangkat = PerangkatEntity(nama = nama, jabatan = jabatan)
                    db.perangkatDao().insertPerangkat(perangkat)
                    finish()
                }
            } else {
                Toast.makeText(this, "Mohon lengkapi formulir!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}