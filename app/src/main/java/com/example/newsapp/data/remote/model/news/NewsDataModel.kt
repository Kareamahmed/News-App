package com.example.newsapp.data.remote.model.news

data class NewsDataModel(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)