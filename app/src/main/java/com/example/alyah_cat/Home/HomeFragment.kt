package com.example.alyah_cat.Home

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.alyah_cat.Home.pertemuan_2.KalkulatorActivity
import com.example.alyah_cat.Home.pertemuan_3.LoginActivity
import com.example.alyah_cat.Home.pertemuan_4.Custom1Activity
import com.example.alyah_cat.Home.pertemuan_4.WebViewActivity
import com.example.alyah_cat.Home.pertemuan_8.DelpanActivity
import com.example.alyah_cat.R
import com.example.alyah_cat.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 1. Setup Judul Toolbar melalui BaseActivity (agar tidak bentrok)
        (requireActivity() as AppCompatActivity).supportActionBar?.title = "Dashboard Bina Desa"

        // Jika Anda ingin menyembunyikan toolbar bawaan fragment ini karena sudah ada toolbar dari BaseActivity,
        // Anda bisa uncomment kode di bawah ini:
        // binding.toolbar.visibility = View.GONE

        // 2. Navigasi Card Click Listener

        // Kalkulator (Pertemuan 2)
        binding.btnkalkulator.setOnClickListener {
            startActivity(Intent(requireContext(), KalkulatorActivity::class.java))
        }

        // Profile (Pertemuan 4 - Custom 1)
        binding.btncustom1.setOnClickListener {
            startActivity(Intent(requireContext(), Custom1Activity::class.java))
        }

        // PERBAIKAN: Message -> Membuka MessageFragment menggunakan viewPager parentActivity
        binding.btnMessage.setOnClickListener {
            // Karena fragment_container sudah diganti dengan ViewPager2 di BaseActivity,
            // Jika Anda ingin tombol ini memicu perpindahan ke halaman tertentu (misal halaman About/lainnya),
            // Anda bisa memanggil viewPager dari activity seperti ini:
            val viewPager = requireActivity().findViewById<androidx.viewpager2.widget.ViewPager2>(R.id.viewPager)

            // Contoh: Menggeser paksa ke halaman indeks ke-1 (Halaman About) atau sesuaikan kebutuhan Anda
            viewPager.currentItem = 1

            /* CATATAN: Jika MessageFragment ingin dijadikan Activity tersendiri agar bisa dibuka menumpuk,
            sangat disarankan mengubah MessageFragment menjadi MessageActivity, lalu dipanggil menggunakan:
            startActivity(Intent(requireContext(), MessageActivity::class.java))
            */
        }

        // Web View (Pertemuan 4)
        binding.btnwebview.setOnClickListener {
            startActivity(Intent(requireContext(), WebViewActivity::class.java))
        }

        // Layanan Bina Desa -> Masuk ke DelpanActivity (Pertemuan 8)
        binding.btnLayanan.setOnClickListener {
            startActivity(Intent(requireContext(), DelpanActivity::class.java))
        }

        binding.btnPerangkat.setOnClickListener {
            val intent = Intent(requireContext(), com.example.alyah_cat.Home.pertemuan_10.TenthActivity::class.java)
            startActivity(intent)
        }

        // 3. Logika Logout
        binding.btnLogout.setOnClickListener {
            showLogoutDialog()
        }
    }

    private fun showLogoutDialog() {
        AlertDialog.Builder(requireContext())
            .setTitle("Konfirmasi Logout")
            .setMessage("Apakah Anda yakin ingin keluar dari layanan Bina Desa?")
            .setPositiveButton("Ya") { _, _ ->
                val sharedPref = requireContext().getSharedPreferences("USER_PREF", Context.MODE_PRIVATE)
                sharedPref.edit().clear().apply()

                val intent = Intent(requireContext(), LoginActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
                requireActivity().finish()
            }
            .setNegativeButton("Batal", null)
            .show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}