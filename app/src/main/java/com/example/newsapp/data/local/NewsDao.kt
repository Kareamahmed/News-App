package com.example.newsapp.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.newsapp.data.remote.model.news.Article
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsDao {
    @Upsert
    suspend fun upsert(article: Article)

    @Delete
    suspend fun delete(article: Article)

    @Query("SELECT * FROM Article")
    fun getNews(): Flow<List<Article>>

    @Query("SELECT * FROM Article WHERE url=:url")
    suspend fun selectArticle ( url : String) : Article?

}