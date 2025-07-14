package com.example.newsapp.presentation.onbording.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.newsapp.R
import com.example.newsapp.presentation.Dimens.MediumPadding
import com.example.newsapp.presentation.onbording.Page
import com.example.newsapp.presentation.onbording.listOfPages
import com.example.newsapp.ui.theme.NewsAppTheme

@Composable
fun OnBoardingPage(
    modifier: Modifier = Modifier,
    page: Page,
) {
    Column(modifier = modifier) {
        Image(
            modifier = Modifier.fillMaxWidth().fillMaxHeight(0.6f),
            painter =painterResource(page.image),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        Spacer(Modifier.height(MediumPadding))
        Text(
            modifier = Modifier.padding(horizontal = MediumPadding),
            text = page.title,
           style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.Bold),
            color = colorResource(R.color.display_small)
        )

        Text(
            modifier = Modifier.padding(horizontal = MediumPadding),
            text = page.description,
            style = MaterialTheme.typography.bodyMedium,
            color = colorResource(R.color.text_medium)
        )
    }
}

@Preview(showBackground = true)
@Preview(uiMode = UI_MODE_NIGHT_YES,showBackground = true )
@Composable
private fun OnBoardingPagePreview() {
    NewsAppTheme {
        OnBoardingPage(page = listOfPages[0])
    }
}