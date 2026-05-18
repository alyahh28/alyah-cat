package com.example.alyah_cat

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.alyah_cat.About.AboutFragment
import com.example.alyah_cat.Home.HomeFragment
import com.example.alyah_cat.Profile.ProfileFragment
import com.example.alyah_cat.Settings.SettingsFragment

class ViewPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    // Jumlah halaman kita ada 4 (Home, About, Settings, Profile)
    override fun getItemCount(): Int = 4

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> HomeFragment()
            1 -> AboutFragment()
            2 -> SettingsFragment()
            3 -> ProfileFragment()
            else -> HomeFragment()
        }
    }
}