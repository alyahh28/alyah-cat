package com.example.alyah_cat.Home.pertemuan_10

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.alyah_cat.data.AppDatabase
import com.example.alyah_cat.data.entity.PerangkatEntity
import com.example.alyah_cat.databinding.FragmentTabCBinding
import kotlinx.coroutines.launch

class TabCFragment : Fragment() {
    private var _binding: FragmentTabCBinding? = null
    private val binding get() = _binding!!

    private lateinit var db: AppDatabase
    private lateinit var adapter: WargaAdapter
    private val listPerangkat = mutableListOf<PerangkatEntity>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTabCBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Inisialisasi Database Room & Adapter lokal
        db = AppDatabase.getInstance(requireContext())
        adapter = WargaAdapter(listPerangkat, this)

        // Mengatur susunan list menjadi Grid Layout (2 Kolom ke samping)
        binding.rvWarga.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.rvWarga.adapter = adapter

        // Aksi ketika tombol tambah diklik untuk membuka form Activity
        binding.btnAddPerangkatMenu.setOnClickListener {
            startActivity(Intent(requireContext(), AddPerangkatActivity::class.java))
        }
    }

    // Fungsi membaca/mengambil data dari tabel perangkat_desa
    private fun loadPerangkat() {
        viewLifecycleOwner.lifecycleScope.launch {
            val data = db.perangkatDao().getAllPerangkat()
            listPerangkat.clear()
            listPerangkat.addAll(data)
            adapter.notifyDataSetChanged()
        }
    }

    // Fungsi menghapus satu baris data perangkat desa
    fun hapusPerangkat(perangkat: PerangkatEntity) {
        viewLifecycleOwner.lifecycleScope.launch {
            db.perangkatDao().deletePerangkat(perangkat)
            loadPerangkat() // Refresh list setelah dihapus
        }
    }

    // Mengambil ulang data otomatis ketika form input selesai di-close (finish)
    override fun onResume() {
        super.onResume()
        loadPerangkat()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}