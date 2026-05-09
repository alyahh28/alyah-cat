package com.example.alyah_cat.About

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SimpleAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.alyah_cat.databinding.FragmentAboutBinding

class AboutFragment : Fragment() {

    private var _binding: FragmentAboutBinding? = null
    private val binding get() = _binding!!

    // Data List untuk Struktur Organisasi Desa
    private val bidangDesaList = listOf(
        mapOf("bidang" to "Sekretariat Desa", "desc" to "Pusat pelayanan administrasi warga"),
        mapOf("bidang" to "Seksi Pemerintahan", "desc" to "Mengelola kependudukan & sipil"),
        mapOf("bidang" to "Seksi Kesejahteraan", "desc" to "Pembangunan ekonomi & bantuan sosial"),
        mapOf("bidang" to "Seksi Pelayanan", "desc" to "Penyuluhan dan keamanan lingkungan")
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAboutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Setup Toolbar tanpa judul agar desain header XML terlihat bersih
        val activity = (requireActivity() as AppCompatActivity)
        activity.setSupportActionBar(binding.toolbar)
        activity.supportActionBar?.setDisplayShowTitleEnabled(false)

        // Set SimpleAdapter dengan layout simple_list_item_2 agar ada sub-item
        val adapter = SimpleAdapter(
            requireContext(),
            bidangDesaList,
            android.R.layout.simple_list_item_2,
            arrayOf("bidang", "desc"),
            intArrayOf(android.R.id.text1, android.R.id.text2)
        )

        binding.listViewBidang.adapter = adapter

        // Listener saat item list diklik
        binding.listViewBidang.setOnItemClickListener { _, _, position, _ ->
            val selected = bidangDesaList[position]
            Toast.makeText(
                requireContext(),
                "Informasi Detail: ${selected["bidang"]}",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}