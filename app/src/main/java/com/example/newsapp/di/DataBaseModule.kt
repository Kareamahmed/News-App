package com.example.newsapp.di

import android.content.Context
import androidx.room.Room
import com.example.newsapp.data.local.NewsDataBase
import com.example.newsapp.data.local.NewsTypeConvertor
import com.example.newsapp.util.Constant.NEWS_DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {

    @Provides
    @Singleton
    fun provideRoomDataBase(
        @ApplicationContext
        context: Context,
    ): NewsDataBase {
        return Room.databaseBuilder(
            context = context,
            klass = NewsDataBase::class.java,
            name = NEWS_DATABASE_NAME
        ).addTypeConverter(NewsTypeConvertor())
            .fallbackToDestructiveMigration()
            .build()
    }
    @Provides
    @Singleton
    fun provideNewsDao(roomDataBase: NewsDataBase) = roomDataBase.dao()

}