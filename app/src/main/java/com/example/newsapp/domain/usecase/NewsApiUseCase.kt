package com.example.newsapp.domain.usecase

import androidx.paging.PagingData
import com.example.newsapp.data.remote.model.news.Article
import com.example.newsapp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class GetNewsUseCase(
    private val newsRepository: NewsRepository,
) {
    operator fun invoke(sources: List<String>): Flow<PagingData<Article>> {
        return newsRepository.getNews(sources = sources)
    }
}