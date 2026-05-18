package com.example.alyah_cat.Home.pertemuan_10

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.alyah_cat.R

class PerangkatAdapter(private val list: List<PerangkatModel>) :
    RecyclerView.Adapter<PerangkatAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val img: ImageView = view.findViewById(R.id.imgPerangkat)
        val nama: TextView = view.findViewById(R.id.tvNamaPerangkat)
        val jabatan: TextView = view.findViewById(R.id.tvJabatanPerangkat)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_perangkat, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.nama.text = item.nama
        holder.jabatan.text = item.jabatan
        holder.img.setImageResource(item.imageRes)
    }

    override fun getItemCount(): Int = list.size
}