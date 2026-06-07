package com.example.alyah_cat.Home.pertemuan_10

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.alyah_cat.R
import com.example.alyah_cat.databinding.ActivityTenthBinding
import com.google.android.material.tabs.TabLayoutMediator

class TenthActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTenthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTenthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Setup Toolbar & Tombol Kembali (Back Action)
        setSupportActionBar(binding.toolbar)
        binding.toolbar.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        // 1. Inisialisasi Adapter Sesuai Panduan Modul Poin 4
        val tabsAdapter = TenthTabsAdapter(this)

        // 2. Pasang Adapter ke ViewPager2
        binding.viewPager.adapter = tabsAdapter

        // 3. Hubungkan TabLayout & ViewPager2 serta Customisasi Ikon + Badge (Poin 5)
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = "Pimpinan"
                    tab.icon = ContextCompat.getDrawable(this, R.drawable.ic_profile)

                    // Badge Titik Tanpa Nomor
                    val badge = tab.getOrCreateBadge()
                    badge.isVisible = true
                }
                1 -> {
                    tab.text = "Staf Umum"
                    tab.icon = ContextCompat.getDrawable(this, R.drawable.ic_profile)

                    // Badge Dengan Nomor Notifikasi
                    val badge = tab.getOrCreateBadge()
                    badge.isVisible = true
                    badge.number = 4
                }
                2 -> {
                    tab.text = "Data Warga" // Judul untuk TabC
                    tab.icon = ContextCompat.getDrawable(this, R.drawable.ic_profile)
                }
            }
        }.attach()
    }
}