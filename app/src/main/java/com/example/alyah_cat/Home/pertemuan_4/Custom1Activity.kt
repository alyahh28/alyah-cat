package com.example.alyah_cat.Home.pertemuan_4

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.alyah_cat.Home.pertemuan_3.LoginActivity
import com.example.alyah_cat.R
import android.widget.TextView
import com.google.android.material.card.MaterialCardView

class Custom1Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom1)

        // Mengambil data username dari SharedPreferences
        val sharedPref = getSharedPreferences("user_pref", MODE_PRIVATE)
        val username = sharedPref.getString("username", "Pengguna Desa")

        // Inisialisasi View
        val tvName = findViewById<TextView>(R.id.tv_profile_name)
        val btnSignOut = findViewById<MaterialCardView>(R.id.btn_profile_signout)

        tvName.text = username

        // Logika Sign Out
        btnSignOut.setOnClickListener {
            val editor = sharedPref.edit()
            editor.clear() // Hapus isLogin
            editor.apply()

            val intent = Intent(this, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }
    }
}