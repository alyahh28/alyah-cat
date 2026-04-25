package com.example.alyah_cat

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.alyah_cat.R
import com.example.alyah_cat.pertemuan_3.LoginActivity // Pastikan path ini benar
import com.example.alyah_cat.pertemuan_4.DashboardActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        lifecycleScope.launch {
            delay(2000) // Delay 2 detik sesuai instruksi

            // Mengambil SharedPreferences dengan nama "user_pref"
            val sharedPref = getSharedPreferences("user_pref", Context.MODE_PRIVATE)
            val isLogin = sharedPref.getBoolean("isLogin", false)

            // Pengecekan status isLogin dipindahkan ke sini
            val intent = if (isLogin) {
                // Jika isLogin bernilai true -> ke MainActivity/Dashboard
                Intent(this@SplashScreenActivity, DashboardActivity::class.java)
            } else {
                // Jika isLogin bernilai false -> ke LoginActivity (AuthActivity)
                Intent(this@SplashScreenActivity, LoginActivity::class.java)
            }

            startActivity(intent)
            finish() // Menutup Splash Screen agar tidak bisa di-back
        }
    }
}