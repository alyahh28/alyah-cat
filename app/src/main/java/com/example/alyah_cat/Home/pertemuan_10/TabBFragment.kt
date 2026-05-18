package com.example.alyah_cat.Home.pertemuan_10

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager // IMPORT BARU
import com.example.alyah_cat.databinding.FragmentTabBBinding

class TabBFragment : Fragment() {

    private var _binding: FragmentTabBBinding? = null
    private val binding get() = _binding!!

    // Mengisi list data tiruan bertema Staf Umum dan Kewilayahan
    private val stafList = listOf(
        ProductModel("Siti Aminah", "Kaur Keuangan", ""),
        ProductModel("Budi Santoso, S.Sos", "Kaur Pemerintahan", ""),
        ProductModel("H. Supriadi", "Kepala Dusun / RW 02", ""),
        ProductModel("Joko Widodo", "Ketua RT 04", ""),
        ProductModel("Susi Susanti", "Staf Administrasi", ""),
        ProductModel("Bambang Pamungkas", "Kasi Pelayanan", "")
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTabBBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Inisialisasi adapter dengan data staf sesuai modul
        val productAdapter = ProductAdapter(stafList) { selectedItem ->
            Toast.makeText(requireContext(), "Profil: ${selectedItem.name} (${selectedItem.price})", Toast.LENGTH_SHORT).show()
        }

        // --- BAGIAN YANG DIUBAH ---
        // Mengubah LinearLayoutManager menjadi GridLayoutManager dengan 2 kolom (gaya Shopee)
        binding.rvTabB.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.rvTabB.adapter = productAdapter
        binding.rvTabB.setHasFixedSize(true)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}