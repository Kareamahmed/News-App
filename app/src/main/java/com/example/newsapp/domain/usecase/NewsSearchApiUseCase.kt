package com.example.newsapp.domain.usecase

import androidx.paging.PagingData
import com.example.newsapp.data.remote.model.news.Article
import com.example.newsapp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NewsSearchApiUseCase @Inject constructor(
    private val newsRepository: NewsRepository,
) {
    operator fun invoke(search: String, sources: List<String>): Flow<PagingData<Article>> {
        return newsRepository.getSearchNews(search = search, sources = sources)
    }
}