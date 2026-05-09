package com.example.alyah_cat.Message

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.alyah_cat.R
import com.example.alyah_cat.databinding.FragmentMessageBinding

class MessageFragment : Fragment() {
    private var _binding: FragmentMessageBinding? = null
    private val binding get() = _binding!!

    // Isi dengan data Bina Desa dan gambar dari drawable kamu
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

        // Gunakan warna biru gradasi pada toolbar lewat XML-nya
        binding.toolbar.title = "Pesan Bina Desa"

        val adapter = MessageAdapter(requireContext(), messageList)
        binding.listMessageItems.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}