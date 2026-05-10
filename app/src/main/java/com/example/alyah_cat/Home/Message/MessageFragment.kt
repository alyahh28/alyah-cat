package com.example.alyah_cat.Home.Message

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity // Tambahkan import ini
import com.example.alyah_cat.R
import com.example.alyah_cat.databinding.FragmentMessageBinding

class MessageFragment : Fragment() {
    private var _binding: FragmentMessageBinding? = null
    private val binding get() = _binding!!

    // Data pesan Bina Desa menggunakan icon yang tersedia di folder drawable Anda
    private val messageList = listOf(
        MessageModel("Admin Desa", "Laporan Infrastruktur Anda sedang diproses.", R.drawable.ic_admin),
        MessageModel("Pak Kades", "Terima kasih atas sarannya untuk pembangunan jalan.", R.drawable.ic_kades),
        MessageModel("Layanan Kesehatan", "Jadwal Posyandu bulan depan sudah tersedia.", R.drawable.ic_health),
        MessageModel("Keamanan RT 04", "Ronda malam malam ini dimulai pukul 21.00.", R.drawable.ic_security),
        MessageModel("Bina Desa Center", "Update status permohonan surat administrasi.", R.drawable.ic_office)
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMessageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 1. Setup Toolbar agar judul "Pesan Bina Desa" muncul di header
        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)
        (requireActivity() as AppCompatActivity).supportActionBar?.title = "Pesan Bina Desa"

        // 2. Setup Adapter untuk ListView
        val adapter = MessageAdapter(requireContext(), messageList)
        binding.listMessageItems.adapter = adapter

        // 3. Menghilangkan divider agar tampilan CardView lebih rapi (tidak ada garis di sela-sela)
        binding.listMessageItems.divider = null
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}