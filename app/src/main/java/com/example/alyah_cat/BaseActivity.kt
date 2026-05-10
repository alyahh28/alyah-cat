package com.example.alyah_cat

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.alyah_cat.About.AboutFragment
import com.example.alyah_cat.Home.HomeFragment
import com.example.alyah_cat.Settings.SettingsFragment
import com.example.alyah_cat.Profile.ProfileFragment
import com.example.alyah_cat.databinding.ActivityBaseBinding

class BaseActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBaseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityBaseBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        // Gunakan toolbar dengan bg_blue_gradient dari layout
        setSupportActionBar(binding.toolbarMain)

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, 0)
            insets
        }

        if (savedInstanceState == null) {
            replaceFragment(HomeFragment(), "Bina Desa - Home")
        }

        binding.bottomNavView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home -> replaceFragment(HomeFragment(), "Bina Desa - Home")
                R.id.about -> replaceFragment(AboutFragment(), "Tentang Desa")
                R.id.settings -> replaceFragment(SettingsFragment(), "Pengaturan") // Implementasi Settings
                R.id.profile -> replaceFragment(ProfileFragment(), "Profil Pengembang")
                else -> false
            }
            true
        }
    }

    private fun replaceFragment(fragment: Fragment, title: String) {
        supportActionBar?.title = title
        supportFragmentManager.beginTransaction()
            .replace(binding.fragmentContainer.id, fragment)
            .commit()
    }
}