package com.example.alyah_cat

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.viewpager2.widget.ViewPager2
import com.example.alyah_cat.databinding.ActivityBaseBinding

class BaseActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBaseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityBaseBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        setSupportActionBar(binding.toolbarMain)

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, 0)
            insets
        }

        // 1. Inisialisasi Adapter dan Pasang ke ViewPager2
        val adapter = ViewPagerAdapter(this)
        binding.viewPager.adapter = adapter

        // 2. Logika saat tombol Bottom Navigation DIKLIK (ViewPager pindah halaman)
        binding.bottomNavView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home -> {
                    binding.viewPager.currentItem = 0
                    true
                }
                R.id.about -> {
                    binding.viewPager.currentItem = 1
                    true
                }
                R.id.settings -> {
                    binding.viewPager.currentItem = 2
                    true
                }
                R.id.profile -> {
                    binding.viewPager.currentItem = 3
                    true
                }
                else -> false
            }
        }

        // 3. Logika saat layar DI-SWIPE (Ikon Bottom Nav & Judul Toolbar ikut berubah)
        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)

                // Sinkronisasi ikon menu bawah yang aktif dan judul toolbar atas
                when (position) {
                    0 -> {
                        binding.bottomNavView.selectedItemId = R.id.home
                        supportActionBar?.title = "Bina Desa - Home"
                    }
                    1 -> {
                        binding.bottomNavView.selectedItemId = R.id.about
                        supportActionBar?.title = "Tentang Desa"
                    }
                    2 -> {
                        binding.bottomNavView.selectedItemId = R.id.settings
                        supportActionBar?.title = "Pengaturan"
                    }
                    3 -> {
                        binding.bottomNavView.selectedItemId = R.id.profile
                        supportActionBar?.title = "Profil Pengembang"
                    }
                }
            }
        })
    }
}