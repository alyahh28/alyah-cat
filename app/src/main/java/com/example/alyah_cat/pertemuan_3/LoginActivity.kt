package com.example.alyah_cat.pertemuan_3

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.alyah_cat.R
import com.example.alyah_cat.pertemuan_4.DashboardActivity
import com.google.android.material.button.MaterialButton

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 1. Definisikan SharedPreferences (Gunakan nama "user_pref" sesuai instruksi)
        val sharedPref = getSharedPreferences("user_pref", Context.MODE_PRIVATE)

        // 2. Kondisi jika isLogin bernilai true
        val isLogin = sharedPref.getBoolean("isLogin", false)
        if (isLogin) {
            // Langsung panggil Intent ke DashboardActivity
            startActivity(Intent(this, DashboardActivity::class.java))
            finish()
            return
        }

        // Jika false, baru tampilkan layout login
        setContentView(R.layout.activity_login)

        val btnLogin = findViewById<MaterialButton>(R.id.btn_login)
        val etUsername = findViewById<EditText>(R.id.et_username)
        val etPassword = findViewById<EditText>(R.id.et_password)

        btnLogin.setOnClickListener {
            val username = etUsername.text.toString().trim()
            val password = etPassword.text.toString().trim()

            // Verifikasi login sederhana
            if (username == "admin" && password == "1234") {
                // 3. Set isLogin menjadi true dan simpan username jika berhasil
                val editor = sharedPref.edit()
                editor.putBoolean("isLogin", true)
                editor.putString("username", username)
                editor.apply()

                Toast.makeText(this, "Login Berhasil!", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, DashboardActivity::class.java))
                finish()
            } else {
                Toast.makeText(this, "Username atau Password Salah", Toast.LENGTH_SHORT).show()
            }
        }
    }
}