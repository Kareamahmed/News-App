package com.example.newsapp.domain.repository

import androidx.paging.PagingData
import com.example.newsapp.data.remote.model.news.Article
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    fun getNews(sources: List<String>): Flow<PagingData<Article>>
    fun getSearchNews(search: String, sources: List<String>): Flow<PagingData<Article>>

    //Room
    suspend fun upsert(article: Article)
    suspend fun delete(article: Article)
    fun getBookMark(): Flow<List<Article>>
    suspend fun selectArticle(url: String): Article?
}


