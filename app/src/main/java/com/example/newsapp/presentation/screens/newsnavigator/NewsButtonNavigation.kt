package com.example.newsapp.presentation.screens.newsnavigator

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.newsapp.R
import com.example.newsapp.presentation.screens.Dimens.IconSize
import com.example.newsapp.presentation.screens.Dimens.SmallPadding
import com.example.newsapp.ui.theme.NewsAppTheme

@Composable
fun NewsButtonNavigation(
    selected: Int,
    onItemClick: (Int) -> Unit,
) {
    NavigationBar(
        modifier = Modifier.fillMaxWidth(),
        containerColor = MaterialTheme.colorScheme.background,
        tonalElevation = 10.dp
    ) {
        navigationBarItems.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = index == selected,
                onClick = { onItemClick(index) },
                icon = {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(
                            painter = painterResource(id = item.icon),
                            contentDescription = item.text,
                            modifier = Modifier.size(IconSize)
                        )
                        Spacer(modifier = Modifier.height(SmallPadding))
                        Text(text = item.text, style = MaterialTheme.typography.labelSmall)
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.primary,
                    selectedTextColor = MaterialTheme.colorScheme.primary,
                    unselectedIconColor = colorResource(id = R.color.body),
                    unselectedTextColor = colorResource(id = R.color.body),
                    indicatorColor = MaterialTheme.colorScheme.background
                )
            )
        }
    }
}

data class NavigationItemData(
    @DrawableRes val icon: Int,
    val text: String,
)

private val navigationBarItems = listOf<NavigationItemData>(
    NavigationItemData(icon = R.drawable.ic_home, text = "Home"),
    NavigationItemData(icon = R.drawable.ic_search, text = "Search"),
    NavigationItemData(icon = R.drawable.ic_bookmark, text = "Bookmark"),
)

@Preview(showSystemUi = true)
@Composable
private fun NewsButtonNavigationPreview() {
    NewsAppTheme {
        NewsButtonNavigation(
            selected = 1,
            onItemClick = {}
        )
    }
}