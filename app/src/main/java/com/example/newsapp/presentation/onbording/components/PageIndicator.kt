package com.example.newsapp.presentation.onbording.components

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.newsapp.presentation.Dimens.IndicatorSize
import com.example.newsapp.ui.theme.BlueGray
import com.example.newsapp.ui.theme.NewsAppTheme

@Composable
fun PageIndicator(
    modifier: Modifier = Modifier,
    pageSize: Int,
    selectedPage: Int,
) {

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        repeat(pageSize) { page ->
            val isSelected =  page == selectedPage
            val color = if (isSelected) MaterialTheme.colorScheme.primary else BlueGray
            val width = animateDpAsState(targetValue = if (isSelected) 30.dp else 10.dp, label = "")
            Box(
                modifier = Modifier
                    .height(10.dp)
                    .width(width.value)
                    .clip(CircleShape)
                    .background(color)
            )
        }
    }
}

@Preview
@Composable
private fun PageIndicatorPreview() {
    NewsAppTheme {
        PageIndicator(
            pageSize = 3,
            selectedPage = 2
        )
    }
}