package com.example.newsapp.presentation.screens.detail

import com.example.newsapp.data.remote.model.news.Article

sealed class DetailUiEvent {
    data class UpsertDeleteArticle(val article: Article) : DetailUiEvent()
    object RemoveSideEffect : DetailUiEvent()
}