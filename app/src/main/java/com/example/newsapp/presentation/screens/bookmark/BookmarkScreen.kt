package com.example.newsapp.presentation.screens.bookmark

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.newsapp.R
import com.example.newsapp.data.remote.model.news.Article
import com.example.newsapp.presentation.screens.Dimens.MediumPadding1
import com.example.newsapp.presentation.screens.bookmark.viewmodel.BookMarkViewModel
import com.example.newsapp.presentation.screens.common.ArticleList

@Composable
fun BookmarkScreen(
    navigateToDetail: (Article) -> Unit,
) {
    val vm: BookMarkViewModel = hiltViewModel()
    val state by vm.state.collectAsStateWithLifecycle()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = MediumPadding1, start = MediumPadding1, end = MediumPadding1)
    ) {
        Text(
            text = "BookMark",
            style = MaterialTheme.typography.displayMedium.copy(fontWeight = FontWeight.Bold),
            color = colorResource(R.color.text_title)
        )
        HorizontalDivider()
        Spacer(Modifier.height(MediumPadding1))

        ArticleList(articles = state.articles) {
            navigateToDetail(it)
        }

    }


}