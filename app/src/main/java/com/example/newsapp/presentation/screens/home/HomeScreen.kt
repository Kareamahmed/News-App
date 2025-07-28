package com.example.newsapp.presentation.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.newsapp.R
import com.example.newsapp.data.remote.model.news.Article
import com.example.newsapp.presentation.screens.Dimens.LogoSize
import com.example.newsapp.presentation.screens.Dimens.MediumPadding1
import com.example.newsapp.presentation.screens.Dimens.SmallPadding
import com.example.newsapp.presentation.screens.common.ArticleList
import com.example.newsapp.presentation.screens.common.SearchBar
import com.example.newsapp.presentation.screens.home.viewmodel.HomeViewModel
import com.example.newsapp.ui.theme.NewsAppTheme


@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onSearchClick: () -> Unit,
    onItemClick: (Article) -> Unit,
) {
    val vm: HomeViewModel = hiltViewModel()
    val news = vm.news.collectAsLazyPagingItems()
    Column(
        modifier = modifier
            .fillMaxSize()
            .statusBarsPadding()
    ) {
        Image(
            modifier = Modifier
                .padding(horizontal = MediumPadding1)
                .size(LogoSize),
            painter = painterResource(id = R.drawable.ic_logo),
            contentDescription = null
        )
        SearchBar(
            modifier = Modifier.padding(horizontal = MediumPadding1),
            onClick = {
                onSearchClick()
            },
            onSearch = { },
            text = "",
            onValueChange = {},
            readOnly = true
        )
        Spacer(modifier = Modifier.padding(top = SmallPadding))

        ArticleList(modifier = Modifier.padding(horizontal = MediumPadding1), articles = news) {
            onItemClick(it)
        }

    }
}

@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    NewsAppTheme {
        HomeScreen(onItemClick = {}, onSearchClick = {})
    }
}