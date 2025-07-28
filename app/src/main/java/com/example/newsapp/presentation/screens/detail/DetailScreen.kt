package com.example.newsapp.presentation.screens.detail

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.newsapp.R
import com.example.newsapp.data.remote.model.news.Article
import com.example.newsapp.data.remote.model.news.Source
import com.example.newsapp.presentation.screens.Dimens.MediumPadding1
import com.example.newsapp.presentation.screens.detail.components.TopBar
import com.example.newsapp.presentation.screens.detail.viewmodel.DetailViewModel
import com.example.newsapp.ui.theme.NewsAppTheme

@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    article: Article,
    navigateUp: () -> Unit,
) {
    val vm: DetailViewModel = hiltViewModel()

    val context = LocalContext.current
    if (vm.sideEffect != null) {
        Toast.makeText(context, vm.sideEffect, Toast.LENGTH_SHORT).show()
        vm.onEvent(DetailUiEvent.RemoveSideEffect)
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding(),
        topBar = {
            TopBar(
                navigateUp,
                onShareClick = {
                    Intent(Intent.ACTION_SEND).also {
                        it.putExtra(Intent.EXTRA_TEXT, article.url)
                        it.type = "text/plain"
                        if (it.resolveActivity(context.packageManager) != null) {
                            context.startActivity(it)
                        }
                    }

                },
                onNetworkClick = {
                    Intent(Intent.ACTION_VIEW).also {
                        it.data = Uri.parse(article.url)
                        if (it.resolveActivity(context.packageManager) != null) {
                            context.startActivity(it)
                        }
                    }
                },
                onBookMarkClick = {
                    vm.onEvent(DetailUiEvent.UpsertDeleteArticle(article))
                },
            )
        }
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(it)
                .padding(MediumPadding1)
                .verticalScroll(rememberScrollState())

        ) {
            Image(
                painter = rememberAsyncImagePainter(
                    ImageRequest.Builder(LocalContext.current)
                        .data(article.urlToImage)
                        .crossfade(700)
                        .build()
                ),
                contentScale = ContentScale.Crop,
                contentDescription = "article image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
                    .clip(shape = MaterialTheme.shapes.medium)
            )

            Spacer(Modifier.height(MediumPadding1))

            Text(
                text = article.title,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.displaySmall,
                color = colorResource(
                    id = R.color.text_title
                )
            )
            Spacer(Modifier.height(10.dp))
            Text(
                text = article.content,
                style = MaterialTheme.typography.bodyMedium,
                color = colorResource(
                    id = R.color.body
                )
            )

        }
    }
}

@Preview(showBackground = true)
@Composable
private fun DetailScreenPreview() {
    NewsAppTheme {
        DetailScreen(
            article = Article(
                author = "",
                title = "Coinbase says Apple blocked its last app release on NFTs in Wallet ... - CryptoSaurus",
                description = "Coinbase says Apple blocked its last app release on NFTs in Wallet ... - CryptoSaurus",
                content = "We use cookies and data to Deliver and maintain Google services Track outages and protect against spam, fraud, and abuse Measure audience engagement and site statistics to unde… [+1131 chars]",
                publishedAt = "2023-06-16T22:24:33Z",
                source = Source(
                    id = "", name = "bbc"
                ),
                url = "https://consent.google.com/ml?continue=https://news.google.com/rss/articles/CBMiaWh0dHBzOi8vY3J5cHRvc2F1cnVzLnRlY2gvY29pbmJhc2Utc2F5cy1hcHBsZS1ibG9ja2VkLWl0cy1sYXN0LWFwcC1yZWxlYXNlLW9uLW5mdHMtaW4td2FsbGV0LXJldXRlcnMtY29tL9IBAA?oc%3D5&gl=FR&hl=en-US&cm=2&pc=n&src=1",
                urlToImage = "https://media.wired.com/photos/6495d5e893ba5cd8bbdc95af/191:100/w_1280,c_limit/The-EU-Rules-Phone-Batteries-Must-Be-Replaceable-Gear-2BE6PRN.jpg"
            ),
            navigateUp = { TODO() },
        )
    }
}