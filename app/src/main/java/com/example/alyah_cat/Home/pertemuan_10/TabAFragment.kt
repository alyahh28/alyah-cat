package com.example.alyah_cat.Home.pertemuan_10

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.alyah_cat.databinding.FragmentTabABinding

class TabAFragment : Fragment() {

    private var _binding: FragmentTabABinding? = null
    private val binding get() = _binding!!

    // Mengisi list data tiruan bertema Pimpinan sesuai screenshot struktur aplikasi kamu
    private val pimpinanList = listOf(
        ProductModel("H. Ahmad Subarjo", "Kepala Desa", ""),
        ProductModel("Rian Hidayat, S.Kom", "Sekretaris Desa", "")
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTabABinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Membuat fungsi callback klik item (Lambda) yang menampilkan Toast sesuai modul
        val adapter = ProductAdapter(pimpinanList) { selectedItem ->
            Toast.makeText(requireContext(), "Anda memilih ${selectedItem.name}", Toast.LENGTH_SHORT).show()
        }

        binding.rvTabA.apply {
            layoutManager = LinearLayoutManager(requireContext())
            this.adapter = adapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}