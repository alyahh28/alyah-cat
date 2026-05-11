package com.example.alyah_cat.Home.pertemuan_3

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.alyah_cat.R
import com.google.android.material.button.MaterialButton

class ValidationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_validation)

        val sp = getSharedPreferences("user_pref", MODE_PRIVATE)

        // Ambil data dari SharedPreferences
        val nama = sp.getString("reg_nama", "") ?: "-"
        val email = sp.getString("reg_email", "") ?: "-"
        val tgl = sp.getString("reg_tgl", "") ?: "-"
        val gender = sp.getString("reg_gender", "") ?: "-"
        val user = sp.getString("reg_user", "") ?: "-"
        val pass = sp.getString("reg_pass", "") ?: ""
        val confirm = sp.getString("reg_confirm", "") ?: ""

        // Format tampilan teks agar rapi (Gunakan \n untuk baris baru)
        val infoBuilder = StringBuilder()
        infoBuilder.append("Nama\t\t: $nama\n")
        infoBuilder.append("Email\t\t: $email\n")
        infoBuilder.append("Lahir\t\t: $tgl\n")
        infoBuilder.append("Gender\t: $gender\n")
        infoBuilder.append("User\t\t: $user")

        findViewById<TextView>(R.id.tv_display_data).text = infoBuilder.toString()

        // Button Kembali
        findViewById<MaterialButton>(R.id.btn_kembali).setOnClickListener {
            finish()
        }

        // Button Submit (Validasi Akhir)
        findViewById<MaterialButton>(R.id.btn_submit).setOnClickListener {
            // Cek apakah ada yang kosong (Kecuali gender jika opsional, tapi sebaiknya semua dicek)
            if (nama.isEmpty() || email.isEmpty() || user.isEmpty() || pass.isEmpty()) {
                Toast.makeText(this, "Data tidak lengkap!", Toast.LENGTH_SHORT).show()
            } else if (pass != confirm) {
                Toast.makeText(this, "Password tidak cocok!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Registrasi Berhasil!", Toast.LENGTH_SHORT).show()

                // Pindah ke Login dan hapus history registrasi dari ram
                val intent = Intent(this, LoginActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            }
        }
    }
}