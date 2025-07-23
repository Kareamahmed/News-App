package com.example.newsapp.presentation.screens.search

import androidx.paging.PagingData
import com.example.newsapp.data.remote.model.news.Article
import kotlinx.coroutines.flow.Flow

data class SearchUiState(
    val searchQuery: String = "",
    val articles: Flow<PagingData<Article>> ? = null,
)