package com.example.alyah_cat.Home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.alyah_cat.data.model.NewsModel
import com.example.alyah_cat.databinding.ItemNewsSliderBinding
import com.example.alyah_cat.R

class NewsAdapter(private val list: List<NewsModel>) : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {
    inner class ViewHolder(val binding: ItemNewsSliderBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemNewsSliderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.binding.tvNewsTitle.text = item.title
        
        // Menggunakan safe call ?.large dan placeholder agar tidak error jika gambar kosong
        Glide.with(holder.itemView.context)
            .load(item.image?.large)
            .placeholder(R.drawable.bg_blue_gradient)
            .error(R.drawable.bg_blue_gradient)
            .into(holder.binding.imgNews)
    }

    override fun getItemCount(): Int = list.size
}
