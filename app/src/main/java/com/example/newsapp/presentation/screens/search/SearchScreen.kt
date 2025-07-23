package com.example.newsapp.presentation.screens.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.newsapp.presentation.screens.Dimens.MediumPadding1
import com.example.newsapp.presentation.screens.common.ArticleList
import com.example.newsapp.presentation.screens.common.SearchBar
import com.example.newsapp.ui.theme.NewsAppTheme

@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
) {
    val vm: SearchViewModel = hiltViewModel()
    val state by vm.state.collectAsStateWithLifecycle()
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(
                top = MediumPadding1,
                start = MediumPadding1,
                end = MediumPadding1
            )
            .statusBarsPadding()
    ) {
        SearchBar(
            onClick = { },
            onSearch = { vm.onEvent(SearchEvent.SearchNews) },
            text = state.searchQuery,
            onValueChange = { vm.onEvent(SearchEvent.UpdateSearchQuery(it)) },
            readOnly = false
        )
        Spacer(Modifier.height(MediumPadding1))
        state.articles?.let {
            val articles = it.collectAsLazyPagingItems()
            ArticleList(articles = articles) { }
        }


    }
}

@Preview(showSystemUi = true)
@Composable
private fun SearchScreenPreview() {
    NewsAppTheme {
        SearchScreen()
    }
}