package com.example.newsapp.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.newsapp.data.remote.api.NewsSearchApiServer
import com.example.newsapp.data.remote.model.news.Article

class NewsSearchPagingSource(
    private val newsSearchApiServer: NewsSearchApiServer,
    private val search: String,
    private val source: String,
) : PagingSource<Int, Article>() {

    private var totalArticle = 0
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        return try {
            val page = params.key ?: 1
            val news = newsSearchApiServer.getSearchNews(search = search, sources = source, page = page)
            totalArticle += news.articles.size
            val articles = news.articles.distinctBy { it.title }
            LoadResult.Page(
                data = articles,
                nextKey = if (totalArticle == news.totalResults) null else page + 1,
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
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)

        }
    }
}