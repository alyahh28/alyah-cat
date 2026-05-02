package com.example.alyah_cat.Home.pertemuan_2

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.alyah_cat.R

class KalkulatorActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // PASTIKAN memanggil layout kalkulator, bukan activity_main
        setContentView(R.layout.activity_kalkulator)

        val etAlas = findViewById<EditText>(R.id.etAlas)
        val etTinggi = findViewById<EditText>(R.id.etTinggi)
        val etSisi = findViewById<EditText>(R.id.etSisi)
        val btnHitungDatar = findViewById<Button>(R.id.btnHitungDatar)
        val btnHitungRuang = findViewById<Button>(R.id.btnHitungRuang)
        val tvHasilDatar = findViewById<TextView>(R.id.tvHasilDatar)
        val tvHasilRuang = findViewById<TextView>(R.id.tvHasilRuang)

        // Logika Luas Segitiga
        btnHitungDatar.setOnClickListener {
            val alas = etAlas.text.toString()
            val tinggi = etTinggi.text.toString()

            if (alas.isNotEmpty() && tinggi.isNotEmpty()) {
                val hasil = 0.5 * alas.toDouble() * tinggi.toDouble()
                tvHasilDatar.text = "Hasil Luas: $hasil"
            } else {
                Toast.makeText(this, "Isi alas dan tinggi!", Toast.LENGTH_SHORT).show()
            }
        }

        // Logika Volume Kubus
        btnHitungRuang.setOnClickListener {
            val sisi = etSisi.text.toString()
            if (sisi.isNotEmpty()) {
                val hasil = sisi.toDouble() * sisi.toDouble() * sisi.toDouble()
                tvHasilRuang.text = "Hasil Volume: $hasil"
            } else {
                Toast.makeText(this, "Isi sisi kubus!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}