package com.example.newsapp.di

import com.example.newsapp.data.remote.api.NewsApiServer
import com.example.newsapp.data.remote.api.NewsSearchApiServer
import com.example.newsapp.util.Constant.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun getInstantRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun getNewsApiServer(retrofit: Retrofit): NewsApiServer {
        return retrofit.create(NewsApiServer::class.java)
    }

    @Provides
    @Singleton
    fun getNewsSearchApiServer(retrofit: Retrofit): NewsSearchApiServer {
        return retrofit.create(NewsSearchApiServer::class.java)
    }
}