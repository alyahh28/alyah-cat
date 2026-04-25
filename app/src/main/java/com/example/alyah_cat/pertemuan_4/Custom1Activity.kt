package com.example.alyah_cat.pertemuan_4

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.alyah_cat.R

class Custom1Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom1)

        val judul = intent.getStringExtra("EXTRA_JUDUL")
        val deskripsi = intent.getStringExtra("EXTRA_DESKRIPSI")

        findViewById<TextView>(R.id.tvJudul).text = judul
        findViewById<TextView>(R.id.tvDeskripsi).text = deskripsi
    }
}