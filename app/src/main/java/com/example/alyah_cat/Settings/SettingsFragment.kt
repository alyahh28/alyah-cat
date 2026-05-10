package com.example.alyah_cat.Settings

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.alyah_cat.R
import com.example.alyah_cat.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment() {

    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 1. Konfigurasi Toolbar (Gradasi Biru dari XML)
        binding.toolbar.title = "Pengaturan"
        binding.toolbar.setTitleTextColor(resources.getColor(android.R.color.white))

        // 2. Siapkan Data untuk Custom Adapter
        // Pastikan ikon-ikon ini (ic_profile, ic_notification, dll) sudah ada di drawable kamu
        val listSettings = listOf(
            SettingModel("Profil Saya", "Ubah nama dan info akun desa", R.drawable.ic_profile),
            SettingModel("Notifikasi", "Atur suara pemberitahuan laporan", R.drawable.ic_notification),
            SettingModel("Keamanan", "Ganti kata sandi dan privasi data", R.drawable.ic_security),
            SettingModel("Bahasa", "Pilih bahasa (Indonesia/English)", R.drawable.ic_language),
            SettingModel("Pusat Bantuan", "Panduan penggunaan aplikasi Bina Desa", R.drawable.ic_help),
            SettingModel("Tentang Aplikasi", "Versi 1.0.0 - Bina Desa Digital", R.drawable.ic_info)
        )

        // 3. Pasang Custom Adapter ke ListView
        // SettingsAdapter adalah class yang kamu buat sebelumnya
        val adapter = SettingsAdapter(requireContext(), listSettings)
        binding.listViewSettings.adapter = adapter

        // 4. Logika Klik pada Item ListView
        binding.listViewSettings.setOnItemClickListener { _, _, position, _ ->
            val clickedItem = listSettings[position]
            Toast.makeText(requireContext(), "Membuka ${clickedItem.title}", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}