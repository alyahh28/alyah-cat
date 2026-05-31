package com.example.alyah_cat

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.alyah_cat.Home.pertemuan_11.OnboardingActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        lifecycleScope.launch {
            delay(2000)

            // 1. Pastikan menggunakan nama "user_pref" (huruf kecil) agar konsisten dengan Logout
            val sharedPref = getSharedPreferences("user_pref", Context.MODE_PRIVATE)
            val isLogin = sharedPref.getBoolean("isLogin", false)

            // 2. Tentukan tujuan: Jika sudah login ke Home, jika belum ke Onboarding
            // Sesuai permintaan Anda, Onboarding akan selalu muncul sebelum Login
            val intent = if (isLogin) {
                Intent(this@SplashScreenActivity, BaseActivity::class.java)
            } else {
                Intent(this@SplashScreenActivity, OnboardingActivity::class.java)
            }

            startActivity(intent)
            finish() // Menutup Splash agar tidak bisa di-back
        }
    }
}