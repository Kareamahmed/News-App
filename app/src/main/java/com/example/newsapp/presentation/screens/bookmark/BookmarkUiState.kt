package com.example.newsapp.presentation.screens.bookmark

import com.example.newsapp.data.remote.model.news.Article

data class BookmarkUiState(
    val articles: List<Article> = emptyList(),
)