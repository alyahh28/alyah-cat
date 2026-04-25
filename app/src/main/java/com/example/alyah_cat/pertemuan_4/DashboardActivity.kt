package com.example.alyah_cat.pertemuan_4

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.alyah_cat.R
import com.example.alyah_cat.pertemuan_2.KalkulatorActivity
import com.example.alyah_cat.pertemuan_3.LoginActivity
import com.google.android.material.button.MaterialButton

class DashboardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        val btnRumus = findViewById<MaterialButton>(R.id.btn_rumus_bangun_ruang)
        val btnKoleksi = findViewById<MaterialButton>(R.id.btn_custom_1)
        val btnPinjaman = findViewById<MaterialButton>(R.id.btn_custom_2)
        val btnLogout = findViewById<MaterialButton>(R.id.btn_logout)

        btnRumus.setOnClickListener {
            startActivity(Intent(this, KalkulatorActivity::class.java))
        }

        btnKoleksi.setOnClickListener {
            startActivity(Intent(this, Custom1Activity::class.java))
        }

        btnPinjaman.setOnClickListener {
            startActivity(Intent(this, Custom2Activity::class.java))
        }

        btnLogout.setOnClickListener {
            showLogoutDialog()
        }
    }

    private fun showLogoutDialog() {
        AlertDialog.Builder(this)
            .setTitle("Logout")
            .setMessage("Apakah anda yakin ingin keluar?")
            .setPositiveButton("Iya") { dialog, _ ->
                // --- PENYESUAIAN SESUAI GAMBAR INSTRUKSI ---
                val sharedPref = getSharedPreferences("user_pref", Context.MODE_PRIVATE)
                val editor = sharedPref.edit()

                // Menggunakan .clear() untuk menghapus semua data sesi (isLogin & username)
                editor.clear()
                editor.apply()

                dialog.dismiss()

                // Kembali ke Login dan bersihkan tumpukan Activity
                val intent = Intent(this, LoginActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
                finish()
            }
            .setNegativeButton("Tidak") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }
}