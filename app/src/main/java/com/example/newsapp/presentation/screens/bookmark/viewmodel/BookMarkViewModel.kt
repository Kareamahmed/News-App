package com.example.newsapp.presentation.screens.bookmark.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.domain.usecase.DaoUseCase
import com.example.newsapp.presentation.screens.bookmark.BookmarkUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class BookMarkViewModel @Inject constructor(
    private val daoUseCase: DaoUseCase,
) : ViewModel() {
    private var _state = MutableStateFlow(BookmarkUiState())
    val state = _state.asStateFlow()

    init {
        getBookMark()
    }

    private fun getBookMark() {
        daoUseCase.getBookMarkUseCase().onEach {
            _state.value = BookmarkUiState(articles = it.asReversed())
        }.launchIn(viewModelScope)
    }

}