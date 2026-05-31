package com.example.alyah_cat.data.api

import com.example.alyah_cat.data.model.NewsResponse
import retrofit2.http.GET

interface NewsApiService {
    // Slider menggunakan berita CNN (Gunakan endpoint utama)
    @GET("v1/cnn-news")
    suspend fun getCnnNews(): NewsResponse

    // List bawah menggunakan berita Antara (Gunakan endpoint terbaru)
    @GET("v1/antara-news/terbaru")
    suspend fun getAntaraNews(): NewsResponse
}
