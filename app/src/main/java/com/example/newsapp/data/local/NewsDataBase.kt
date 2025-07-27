package com.example.newsapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.newsapp.data.remote.model.news.Article

@Database(entities = [Article::class], version = 1, exportSchema = false)
@TypeConverters(NewsTypeConvertor::class)
abstract class NewsDataBase : RoomDatabase() {
    abstract fun dao(): NewsDao
}