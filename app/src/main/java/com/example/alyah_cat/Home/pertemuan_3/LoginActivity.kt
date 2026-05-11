package com.example.alyah_cat.Home.pertemuan_3

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView // Tambahkan import ini
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.alyah_cat.BaseActivity
import com.example.alyah_cat.R
import com.google.android.material.button.MaterialButton
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val sharedPref = getSharedPreferences("user_pref", MODE_PRIVATE)

        // Inisialisasi View
        val btnLogin = findViewById<MaterialButton>(R.id.btn_login)
        val etUsername = findViewById<EditText>(R.id.et_username)
        val etPassword = findViewById<EditText>(R.id.et_password)

        // Pastikan kamu sudah menambahkan TextView dengan ID tv_register di activity_login.xml
        val tvRegister = findViewById<TextView>(R.id.tv_register)

        // Alur ke Halaman Registrasi (Penting agar bisa testing soal b1)
        tvRegister.setOnClickListener {
            val intent = Intent(this, RegistrationActivity::class.java)
            startActivity(intent)
        }

        btnLogin.setOnClickListener {
            val usernameInput = etUsername.text.toString().trim()
            val passwordInput = etPassword.text.toString().trim()

            // Ambil data yang tersimpan dari hasil registrasi di SharedPreferences
            val registeredUser = sharedPref.getString("reg_user", null)
            val registeredPass = sharedPref.getString("reg_pass", null)

            // Logika Validasi Sesuai Soal b3
            val isDefaultLogin = (usernameInput == passwordInput && usernameInput.isNotEmpty())
            val isRegisteredLogin = (usernameInput == registeredUser && passwordInput == registeredPass && registeredUser != null)

            if (isDefaultLogin || isRegisteredLogin) {
                // Simpan status login
                sharedPref.edit().apply {
                    putBoolean("isLogin", true)
                    putString("username", usernameInput)
                    apply()
                }

                Toast.makeText(this, "Login Berhasil!", Toast.LENGTH_SHORT).show()

                // Pindah ke Home/BaseActivity
                val intent = Intent(this, BaseActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                // Error handling menggunakan MaterialAlertDialog sesuai instruksi soal b3
                MaterialAlertDialogBuilder(this)
                    .setTitle("Login Gagal")
                    .setMessage("Username atau password salah. Silakan cek kembali atau daftar akun baru.")
                    .setPositiveButton("Coba Lagi") { dialog, _ ->
                        dialog.dismiss()
                    }
                    .setCancelable(false)
                    .show()
            }
        }
    }
}