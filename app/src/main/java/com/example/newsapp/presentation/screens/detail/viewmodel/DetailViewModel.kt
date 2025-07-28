package com.example.newsapp.presentation.screens.detail.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.data.remote.model.news.Article
import com.example.newsapp.domain.usecase.DaoUseCase
import com.example.newsapp.presentation.screens.detail.DetailUiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val daoUseCase: DaoUseCase,
) : ViewModel() {
    var sideEffect by mutableStateOf<String?>(null)
        private set

    fun onEvent(event: DetailUiEvent) {
        when (event) {
            is DetailUiEvent.UpsertDeleteArticle -> {
                Log.d("DetailViewModel", "Article to upsert/delete: ${event.article}")
                viewModelScope.launch {
                    val article = daoUseCase.selectArticleUseCase(event.article.url)
                    if (article == null) {
                        Log.d("DetailViewModel", "Upserting article: ${event.article}")
                        upsertArticle(event.article)
                    } else {
                        Log.d("DetailViewModel", "Deleting article: ${event.article}")
                        deleteArticle(event.article)
                    }
                }
            }

            is DetailUiEvent.RemoveSideEffect -> {
                sideEffect = null
            }
        }
    }

    private suspend fun upsertArticle(article: Article) {
        daoUseCase.upsertUseCase(article)
        sideEffect = "Article Saved"
    }

    private suspend fun deleteArticle(article: Article) {
        daoUseCase.deleteUseCase(article)
        sideEffect = "Article Deleted"
    }
}