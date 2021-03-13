package com.example.simplenewsapp.data.local.models

data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)