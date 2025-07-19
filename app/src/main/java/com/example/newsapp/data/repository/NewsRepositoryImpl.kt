package com.example.newsapp.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.newsapp.data.remote.NewsPagingSource
import com.example.newsapp.data.remote.api.NewsApiServer
import com.example.newsapp.data.remote.model.news.Article
import com.example.newsapp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val newsApi: NewsApiServer,
) : NewsRepository {

    override fun getNews(sources: List<String>): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(pageSize = 10),// in each request return 10 articles
            pagingSourceFactory = {
                NewsPagingSource(
                    newsApi = newsApi,
                    source = sources.joinToString(separator = ",")
                )
            }
        ).flow
    }
}