package com.example.alyah_cat

import android.content.Context
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var etAlas: EditText
    private lateinit var etTinggi: EditText
    private lateinit var etSisi: EditText
    private lateinit var tvHasilDatar: TextView
    private lateinit var tvHasilRuang: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main_scrollview)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        etAlas = findViewById(R.id.etAlas)
        etTinggi = findViewById(R.id.etTinggi)
        etSisi = findViewById(R.id.etSisi)
        tvHasilDatar = findViewById(R.id.tvHasilDatar)
        tvHasilRuang = findViewById(R.id.tvHasilRuang)

        val btnLuas = findViewById<Button>(R.id.btnHitungDatar)
        val btnVolume = findViewById<Button>(R.id.btnHitungRuang)

        btnLuas.setOnClickListener {
            val alas = etAlas.text.toString()
            val tinggi = etTinggi.text.toString()

            if (alas.isNotEmpty() && tinggi.isNotEmpty()) {
                val hasil = 0.5 * alas.toDouble() * tinggi.toDouble()
                tvHasilDatar.text = "Hasil Luas: $hasil"
                tutupKeyboard()
                hapusFokus()
            } else {
                Toast.makeText(this, "Alas dan Tinggi harus diisi!", Toast.LENGTH_SHORT).show()
            }
        }

        btnVolume.setOnClickListener {
            val sisi = etSisi.text.toString()

            if (sisi.isNotEmpty()) {
                val s = sisi.toDouble()
                val hasil = s * s * s
                tvHasilRuang.text = "Hasil Volume: $hasil"
                tutupKeyboard()
                hapusFokus()
            } else {
                etSisi.error = "Sisi kosong!"
            }
        }

        findViewById<android.view.View>(R.id.main_scrollview).setOnClickListener {
            tutupKeyboard()
            hapusFokus()
        }
    }

    private fun tutupKeyboard() {
        val view = this.currentFocus
        if (view != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    private fun hapusFokus() {
        etAlas.clearFocus()
        etTinggi.clearFocus()
        etSisi.clearFocus()
    }
}