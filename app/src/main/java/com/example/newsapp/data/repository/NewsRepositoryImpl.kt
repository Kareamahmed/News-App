package com.example.newsapp.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.newsapp.data.local.NewsDao
import com.example.newsapp.data.remote.NewsPagingSource
import com.example.newsapp.data.remote.NewsSearchPagingSource
import com.example.newsapp.data.remote.api.NewsApiServer
import com.example.newsapp.data.remote.api.NewsSearchApiServer
import com.example.newsapp.data.remote.model.news.Article
import com.example.newsapp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val newsApi: NewsApiServer,
    private val newsSearchApiServer: NewsSearchApiServer,
    private val dao: NewsDao,
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

    override fun getSearchNews(search: String, sources: List<String>): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                NewsSearchPagingSource(
                    source = sources.joinToString(separator = ","),
                    search = search,
                    newsSearchApiServer = newsSearchApiServer
                )
            }
        ).flow
    }

    override suspend fun upsert(article: Article) {
        dao.upsert(article)
    }

    override suspend fun delete(article: Article) {
        dao.delete(article)
    }

    override fun getBookMark(): Flow<List<Article>> {
        return dao.getNews()
    }

    override suspend fun selectArticle(url: String): Article? {
        return dao.selectArticle(url)
    }
}