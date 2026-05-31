package com.example.alyah_cat.data.model

data class NewsResponse(
    val data: List<NewsModel>?
)

data class NewsModel(
    val title: String?,
    val link: String?,
    val contentSnippet: String?,
    val image: NewsImage?,
    val thumbnail: String?
)

data class NewsImage(
    val small: String?,
    val large: String?
)
