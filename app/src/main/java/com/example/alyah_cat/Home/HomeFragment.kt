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
import com.example.alyah_cat.Home.pertemuan_4.Custom2Activity
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

        // 1. Setup Toolbar
        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)
        (requireActivity() as AppCompatActivity).supportActionBar?.apply {
            title = "Dashboard Desa"
        }

        // 2. Navigasi menggunakan Card Click Listener
        binding.btnkalkulator.setOnClickListener {
            val intent = Intent(requireContext(), KalkulatorActivity::class.java)
            intent.putExtra("judul", "Pertemuan 2")
            intent.putExtra("description", "Materi Kalkulator Bangun Ruang")
            startActivity(intent)
        }

        binding.btncustom1.setOnClickListener {
            startActivity(Intent(requireContext(), Custom1Activity::class.java))
        }

        binding.btncustom2.setOnClickListener {
            startActivity(Intent(requireContext(), Custom2Activity::class.java))
        }

        binding.btnwebview.setOnClickListener {
            startActivity(Intent(requireContext(), WebViewActivity::class.java))
        }

        binding.btnP9.setOnClickListener {
            startActivity(Intent(requireContext(), DelpanActivity::class.java))
        }

        // 3. Logika Logout
        binding.btnLogout.setOnClickListener {
            showLogoutDialog()
        }
    }

    private fun showLogoutDialog() {
        AlertDialog.Builder(requireContext())
            .setTitle("Logout")
            .setMessage("Apakah Anda yakin ingin keluar?")
            .setPositiveButton("Ya") { _, _ ->
                val sharedPref = requireContext().getSharedPreferences("USER_PREF", Context.MODE_PRIVATE)
                sharedPref.edit().clear().apply()

                val intent = Intent(requireContext(), LoginActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
                requireActivity().finish()
            }
            .setNegativeButton("Tidak", null)
            .show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}