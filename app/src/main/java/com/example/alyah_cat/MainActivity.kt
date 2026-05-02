package com.example.alyah_cat

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.alyah_cat.databinding.ActivityMainBinding
import com.example.alyah_cat.Home.pertemuan_2.KalkulatorActivity
import com.example.alyah_cat.Home.pertemuan_3.LoginActivity
import com.example.alyah_cat.Home.pertemuan_4.Custom1Activity
import com.example.alyah_cat.Home.pertemuan_4.WebViewActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Setup Toolbar
        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = "Dashboard Bina Desa"

        // 1. Tombol Profil Desa (Custom1Activity)
        binding.btnProfilDesa.setOnClickListener {
            val intent = Intent(this, Custom1Activity::class.java)
            // Mengirim data agar halaman profil tidak kosong
            intent.putExtra("EXTRA_JUDUL", "Profil Bina Desa")
            intent.putExtra("EXTRA_DESC", "Program digitalisasi desa untuk meningkatkan efisiensi pelayanan publik dan pemberdayaan masyarakat lokal.")
            startActivity(intent)
        }

        // 2. Tombol Web View (Tugas Pertemuan 6)
        binding.btnWebView.setOnClickListener {
            startActivity(Intent(this, WebViewActivity::class.java))
        }

        // 3. Tombol Kalkulator (Tugas Pertemuan 2)
        binding.btnKalkulator.setOnClickListener {
            startActivity(Intent(this, KalkulatorActivity::class.java))
        }

        // 4. Tombol Logout (Menghapus Sesi)
        binding.btnLogout.setOnClickListener {
            showLogoutDialog()
        }
    }

    private fun showLogoutDialog() {
        AlertDialog.Builder(this).apply {
            setTitle("Konfirmasi Logout")
            setMessage("Apakah Anda yakin ingin keluar dari aplikasi?")
            setPositiveButton("Ya") { _, _ ->
                val sharedPref = getSharedPreferences("user_pref", Context.MODE_PRIVATE)
                sharedPref.edit().clear().apply() // Hapus isLogin

                val intent = Intent(this@MainActivity, LoginActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
                finish()
            }
            setNegativeButton("Tidak", null)
            show()
        }
    }
}