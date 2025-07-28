package com.example.newsapp.presentation.screens.common

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.newsapp.R
import com.example.newsapp.data.remote.model.news.Article
import com.example.newsapp.data.remote.model.news.Source
import com.example.newsapp.presentation.screens.Dimens.ArticleImagePadding
import com.example.newsapp.presentation.screens.Dimens.ArticleImageSize
import com.example.newsapp.presentation.screens.Dimens.SmallIconSize
import com.example.newsapp.presentation.screens.Dimens.SmallPadding
import com.example.newsapp.ui.theme.NewsAppTheme

@Composable
fun ArticleItem(
    modifier: Modifier = Modifier,
    article: Article,
    onItemClick: (Article) -> Unit,
) {

    Row(modifier = modifier
        .fillMaxWidth()
        .clickable { onItemClick(article) }) {
        Image(
            painter = rememberAsyncImagePainter(
                ImageRequest.Builder(LocalContext.current)
                    .data(article.urlToImage)
                    .crossfade(1000)
                    .build()
            ),
            contentScale = ContentScale.Crop,
            contentDescription = "article image",
            modifier = Modifier
                .padding(ArticleImagePadding)
                .size(ArticleImageSize)
                .clip(shape = MaterialTheme.shapes.medium)
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(ArticleImageSize)
                .padding(SmallPadding),
            verticalArrangement = Arrangement.SpaceAround
        ) {
            Text(
                text = article.title,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.bodyMedium,
                color = colorResource(
                    id = R.color.text_title
                )
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier.padding(end = 7.dp),
                    text = article.source.name,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                    color = colorResource(
                        id = R.color.body
                    )
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_time),
                        contentDescription = null,
                        modifier = Modifier
                            .size(SmallIconSize)
                            .padding(end = SmallPadding),
                        tint = colorResource(id = R.color.body)
                    )
                    Text(
                        text = article.publishedAt,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                        color = colorResource(
                            id = R.color.body
                        )
                    )
                }

            }
        }

    }
    HorizontalDivider()
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun ArticleItemPreview() {
    NewsAppTheme {
        Box(Modifier.background(color = MaterialTheme.colorScheme.background)) {
            ArticleItem(
                article = Article(
                    author = "",
                    content = "Microsoft has suddenly shut its movies and TV store on Xbox consoles and Windows PCs. As of today you can no longer purchase new movies or TV shows from the Microsoft Store on Xbox or Windows, but you will still be able to access previously purchased content …",
                    description = "",
                    publishedAt = "25/4/2022",
                    source = Source(id = "", name = ""),
                    title = "",
                    url = "",
                    urlToImage = ""
                )
            ) { }
        }
    }
}