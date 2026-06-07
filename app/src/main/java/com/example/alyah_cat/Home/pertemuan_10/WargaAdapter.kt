package com.example.alyah_cat.Home.pertemuan_10

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.alyah_cat.R
import com.example.alyah_cat.data.entity.PerangkatEntity
import com.example.alyah_cat.databinding.ItemWargaBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class WargaAdapter(
    private val perangkatList: List<PerangkatEntity>,
    private val fragment: TabCFragment
) : RecyclerView.Adapter<WargaAdapter.WargaViewHolder>() {

    inner class WargaViewHolder(val binding: ItemWargaBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WargaViewHolder {
        val binding = ItemWargaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return WargaViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WargaViewHolder, position: Int) {
        val item = perangkatList[position]
        holder.binding.tvWargaName.text = item.nama
        holder.binding.tvWargaStatus.text = item.jabatan

        // Memasang gambar icon profile bawaan sebagai default
        holder.binding.imgWarga.setImageResource(R.drawable.ic_profile)

        // Aksi ketika tombol hapus diklik
        holder.binding.btnDeletePerangkat.setOnClickListener {
            MaterialAlertDialogBuilder(holder.itemView.context)
                .setTitle("Hapus Perangkat")
                .setMessage("Apakah Anda yakin ingin menghapus data ${item.nama}?")
                .setPositiveButton("Hapus") { dialog, _ ->
                    fragment.hapusPerangkat(item)
                    dialog.dismiss()
                }
                .setNegativeButton("Batal") { dialog, _ ->
                    dialog.dismiss()
                }
                .show()
        }
    }

    override fun getItemCount(): Int = perangkatList.size
}