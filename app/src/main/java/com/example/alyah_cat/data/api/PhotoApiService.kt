package com.example.alyah_cat.data.api

import com.example.alyah_cat.data.model.PhotoModel
import retrofit2.http.GET

interface PhotoApiService {
    @GET("v2/list")
    suspend fun getPhotos(): List<PhotoModel>
}