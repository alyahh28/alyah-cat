package com.example.alyah_cat.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

data class CatFactResponse(
    val fact: String,
    val length: Int
)

interface CatFactApiService {
    @GET("fact")
    suspend fun getCatFact(): CatFactResponse
}

object CatFactApiClient {
    private const val BASE_URL = "https://catfact.ninja/"

    val apiService: CatFactApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CatFactApiService::class.java)
    }
}