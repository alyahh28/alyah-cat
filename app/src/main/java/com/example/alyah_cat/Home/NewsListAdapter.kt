package com.example.alyah_cat.Home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.alyah_cat.R
import com.example.alyah_cat.data.model.NewsModel
import com.example.alyah_cat.databinding.ItemNewsListBinding

class NewsListAdapter(private val list: List<NewsModel>) : RecyclerView.Adapter<NewsListAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemNewsListBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemNewsListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.binding.tvTitleList.text = item.title
        holder.binding.tvDescList.text = item.contentSnippet

        // PERBAIKAN: Menggunakan fallback thumbnail jika image.large null
        val imageUrl = item.image?.large ?: item.thumbnail

        Glide.with(holder.itemView.context)
            .load(imageUrl)
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background)
            .into(holder.binding.imgNewsList)
    }

    override fun getItemCount(): Int = list.size
}