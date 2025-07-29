package com.example.newsapp.presentation.screens.onbording

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.newsapp.presentation.screens.Dimens.MediumPadding1
import com.example.newsapp.presentation.screens.common.NewsButton
import com.example.newsapp.presentation.screens.common.NewsTextButton
import com.example.newsapp.presentation.screens.onbording.components.OnBoardingPage
import com.example.newsapp.presentation.screens.onbording.components.PageIndicator
import com.example.newsapp.presentation.screens.onbording.viewmodel.OnBoardingViewModel
import kotlinx.coroutines.launch


@Composable
fun OnBoardingScreen(modifier: Modifier = Modifier, event: (OnBoardingEvent) -> Unit) {
    val pagerState = rememberPagerState(pageCount = { listOfPages.size })
    val buttonState = remember {
        derivedStateOf {
            when (pagerState.currentPage) {
                0 -> listOf("", "Next")
                1 -> listOf("Back", "Next")
                2 -> listOf("Back", "Get Started")
                else -> listOf("", "")
            }
        }
    }
    Column(modifier = modifier.fillMaxSize()) {
        HorizontalPager(state = pagerState) { index ->
            OnBoardingPage(page = listOfPages[index])
        }
        Spacer(Modifier.weight(1f))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = MediumPadding1)
                .navigationBarsPadding(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            PageIndicator(
                modifier = Modifier.width(65.dp),
                pageSize = pagerState.pageCount,
                selectedPage = pagerState.currentPage
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
                val scope = rememberCoroutineScope()
                if (buttonState.value[0].isNotEmpty()) {
                    NewsTextButton(text = buttonState.value[0]) {
                        scope.launch {
                            pagerState.animateScrollToPage(page = pagerState.currentPage - 1)
                        }
                    }
                }
                NewsButton(text = buttonState.value[1]) {
                    scope.launch {
                        if (pagerState.currentPage == 2) {
                            event(OnBoardingEvent.SaveAppEntry)
                        } else
                            pagerState.animateScrollToPage(page = pagerState.currentPage + 1)
                    }
                }
            }
        }

    }
}