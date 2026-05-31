package com.example.alyah_cat.Home.pertemuan_11

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.alyah_cat.Home.pertemuan_3.LoginActivity
import com.example.alyah_cat.databinding.ActivityOnboardingBinding

class OnboardingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOnboardingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnboardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fragmentsList = listOf(Tutorial1Fragment(), Tutorial2Fragment(), Tutorial3Fragment())
        val adapter = OnboardingAdapter(this, fragmentsList)
        binding.onboardingViewPager.adapter = adapter

        // Hubungkan Dots Indicator ke ViewPager
        binding.dotIndicator.attachTo(binding.onboardingViewPager)

        // Logika memunculkan tombol "Ayo Mulai" hanya di slide terakhir
        binding.onboardingViewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                if (position == fragmentsList.size - 1) {
                    binding.btnStart.visibility = View.VISIBLE
                } else {
                    binding.btnStart.visibility = View.GONE
                }
            }
        })

        // Arahkan ke Halaman Login saat tombol diklik
        binding.btnStart.setOnClickListener {
            // Gunakan "user_pref" (huruf kecil)
            val sharedPref = getSharedPreferences("user_pref", Context.MODE_PRIVATE)
            sharedPref.edit().putBoolean("isOnboardingFinished", true).apply()

            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}