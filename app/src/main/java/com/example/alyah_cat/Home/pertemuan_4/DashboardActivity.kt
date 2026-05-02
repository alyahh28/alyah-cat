package com.example.alyah_cat.Home.pertemuan_4

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.alyah_cat.R
import com.example.alyah_cat.Home.pertemuan_4.WebViewActivity
import com.example.alyah_cat.Home.pertemuan_2.KalkulatorActivity
import com.example.alyah_cat.Home.pertemuan_3.LoginActivity
import com.google.android.material.button.MaterialButton

class DashboardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        // Inisialisasi Tombol sesuai ID di activity_dashboard.xml
        val btnKalkulator = findViewById<MaterialButton>(R.id.btn_rumus_bangun_ruang)
        val btnWebView = findViewById<MaterialButton>(R.id.btn_custom_1)
        val btnProfilDesa = findViewById<MaterialButton>(R.id.btn_custom_2)
        val btnLogout = findViewById<MaterialButton>(R.id.btn_logout)

        // Update Teks Tombol agar sesuai Topik Bina Desa
        btnWebView.text = "Buka Web Bina Desa"
        btnProfilDesa.text = "Profil Bina Desa"

        // Navigasi ke Kalkulator (Tugas Pertemuan 2)
        btnKalkulator.setOnClickListener {
            startActivity(Intent(this, KalkulatorActivity::class.java))
        }

        // Navigasi ke WebView Bina Desa (Tugas Pertemuan 6)
        btnWebView.setOnClickListener {
            startActivity(Intent(this, WebViewActivity::class.java))
        }

        // Navigasi ke Halaman Custom (Tugas Pertemuan 4)
        btnProfilDesa.setOnClickListener {
            val intent = Intent(this, Custom1Activity::class.java)
            intent.putExtra("EXTRA_JUDUL", "Profil Program Bina Desa")
            intent.putExtra("EXTRA_DESC", "Program ini bertujuan untuk digitalisasi administrasi desa melalui platform Perangkat Guest.")
            startActivity(intent)
        }

        // Fungsi Logout
        btnLogout.setOnClickListener {
            showLogoutDialog()
        }
    }

    private fun showLogoutDialog() {
        AlertDialog.Builder(this)
            .setTitle("Logout")
            .setMessage("Apakah anda yakin ingin keluar dari aplikasi Bina Desa?")
            .setPositiveButton("Iya") { dialog, _ ->
                // PENTING: Menghapus Sesi Login di SharedPreferences
                val sharedPref = getSharedPreferences("user_pref", MODE_PRIVATE)
                val editor = sharedPref.edit()

                // Menghapus isLogin dan data user lainnya
                editor.clear()
                editor.apply()

                dialog.dismiss()

                // Berpindah ke Login dan membersihkan tumpukan Activity (Clear Task)
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