package com.example.alyah_cat.Home.pertemuan_8

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.alyah_cat.databinding.ActivityDelpanBinding
import com.google.android.material.chip.Chip

class DelpanActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDelpanBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityDelpanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Logika Tombol Back di Toolbar
        binding.toolbar.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        // Logika ChipGroup Filter
        binding.chipGroupFilter.setOnCheckedStateChangeListener { group, checkedIds ->
            val selectedChipId = checkedIds.firstOrNull()
            if (selectedChipId != null) {
                val chip = group.findViewById<Chip>(selectedChipId)
                Toast.makeText(this, "Kategori: ${chip.text}", Toast.LENGTH_SHORT).show()
            }
        }

        // Logika Tombol Kirim (MaterialButton)
        binding.btnKirim.setOnClickListener {
            val nama = binding.etNama.text.toString()
            if (nama.isNotEmpty()) {
                Toast.makeText(this, "Laporan $nama telah terkirim", Toast.LENGTH_LONG).show()
            } else {
                binding.tlNama.error = "Nama tidak boleh kosong"
            }
        }
    }
}