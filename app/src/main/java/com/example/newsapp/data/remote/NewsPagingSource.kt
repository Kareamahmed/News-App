package com.example.newsapp.data.remote


import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.newsapp.data.remote.api.NewsApiServer
import com.example.newsapp.data.remote.model.news.Article

class NewsPagingSource(
    private val newsApi: NewsApiServer,
    private val source: String,
) : PagingSource<Int, Article>() {

    private var totalArticles = 0

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        val page = params.key ?: 1
        return try {
            val news = newsApi.getNews(sources = source, page = page)
            totalArticles += news.articles.size
            val articles = news.articles.distinctBy { it.title } // remove duplicates
            LoadResult.Page(
                data = articles,
                nextKey = if (totalArticles == news.totalResults) null else page + 1,
                prevKey = null
            )

        } catch (e: Exception) {
            e.printStackTrace()
            LoadResult.Error(
                throwable = e
            )
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
       return state.anchorPosition?.let {anchorPosition->
           val anchorPage = state.closestPageToPosition(anchorPosition)
           anchorPage?.prevKey?.plus(1)?: anchorPage?.nextKey?.minus(1)

       }
    }
}