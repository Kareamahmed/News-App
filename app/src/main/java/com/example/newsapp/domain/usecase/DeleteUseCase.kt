package com.example.newsapp.domain.usecase

import com.example.newsapp.data.remote.model.news.Article
import com.example.newsapp.domain.repository.NewsRepository
import javax.inject.Inject

class DeleteUseCase(
    private val newsRepository: NewsRepository,
) {
    suspend operator fun invoke(article: Article) {
        newsRepository.delete(article)
    }
}