package com.example.alyah_cat.Home.pertemuan_4

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.alyah_cat.R

class Custom2Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom2)

        val judul = intent.getStringExtra("EXTRA_JUDUL")
        val deskripsi = intent.getStringExtra("EXTRA_DESKRIPSI")

        findViewById<TextView>(R.id.tv_judul_custom2).text = judul
        findViewById<TextView>(R.id.tv_desc_custom2).text = deskripsi
    }
}