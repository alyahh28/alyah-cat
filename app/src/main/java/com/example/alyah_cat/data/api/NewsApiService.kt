package com.example.alyah_cat.data.api

import com.example.alyah_cat.data.model.NewsResponse
import retrofit2.http.GET

interface NewsApiService {
    // Slider menggunakan berita CNN
    @GET("v1/cnn-news")
    suspend fun getCnnNews(): NewsResponse

    // List bawah menggunakan berita Antara
    @GET("v1/antara-news")
    suspend fun getAntaraNews(): NewsResponse
}
