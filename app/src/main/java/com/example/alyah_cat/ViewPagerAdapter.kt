package com.example.alyah_cat

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.alyah_cat.About.AboutFragment
import com.example.alyah_cat.Home.HomeFragment
import com.example.alyah_cat.Home.Message.MessageFragment
import com.example.alyah_cat.Profile.ProfileFragment
import com.example.alyah_cat.Settings.SettingsFragment

class ViewPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    // Jumlah halaman kita ada 5 (Home, About, Settings, Profile, Message)
    override fun getItemCount(): Int = 5

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> HomeFragment()
            1 -> AboutFragment()
            2 -> SettingsFragment()
            3 -> ProfileFragment()
            4 -> MessageFragment()
            else -> HomeFragment()
        }
    }
}