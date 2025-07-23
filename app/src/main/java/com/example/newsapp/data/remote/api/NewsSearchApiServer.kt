package com.example.newsapp.data.remote.api

import com.example.newsapp.data.remote.model.news.NewsDataModel
import com.example.newsapp.util.Constant.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsSearchApiServer {
    @GET("everything")
    suspend fun getSearchNews(
        @Query("q") search: String,
        @Query("sources") sources: String,
        @Query("page") page: Int,
        @Query("apikey") apiKey: String = API_KEY,
    ): NewsDataModel
}