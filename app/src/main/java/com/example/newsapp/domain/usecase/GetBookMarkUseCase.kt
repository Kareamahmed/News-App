package com.example.newsapp.domain.usecase

import com.example.newsapp.domain.repository.NewsRepository
import javax.inject.Inject

class GetBookMarkUseCase (
    private val newsRepository: NewsRepository,
) {
    operator fun invoke() = newsRepository.getBookMark()
}