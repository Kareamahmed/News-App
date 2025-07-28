package com.example.newsapp.presentation.screens.newsnavigator

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.newsapp.data.remote.model.news.Article
import com.example.newsapp.presentation.navigation.Routes
import com.example.newsapp.presentation.screens.bookmark.BookmarkScreen
import com.example.newsapp.presentation.screens.detail.DetailScreen
import com.example.newsapp.presentation.screens.home.HomeScreen
import com.example.newsapp.presentation.screens.search.SearchScreen

@Composable
fun NewsNavigator() {
    val navController = rememberNavController()
    val backStackEntry = navController.currentBackStackEntryAsState().value
    var selectedItem by rememberSaveable { mutableIntStateOf(0) }

    selectedItem = when (backStackEntry?.destination?.route) {
        Routes.HomeScreen.route -> 0
        Routes.SearchScreen.route -> 1
        Routes.BookmarkScreen.route -> 2
        else -> 0
    }

    Scaffold(
        bottomBar = {
            NewsButtonNavigation(
                selected = selectedItem,
            ) { index ->
                when (index) {
                    0 -> navigateToTab(navController, Routes.HomeScreen.route)
                    1 -> navigateToTab(navController, Routes.SearchScreen.route)
                    2 -> navigateToTab(navController, Routes.BookmarkScreen.route)
                }
            }
        }
    ) {
        NavHost(
            navController = navController,
            startDestination = Routes.HomeScreen.route,
            modifier = Modifier.padding(it)
        ) {
            composable(route = Routes.HomeScreen.route) {
                HomeScreen(
                    onSearchClick = {
                        navigateToTab(navController, Routes.SearchScreen.route)
                    },
                    onItemClick = {
                        navigateToDetail(navController, article = it)
                    }
                )
            }
            composable(route = Routes.DetailScreen.route) {
                val article =
                    navController.previousBackStackEntry?.savedStateHandle?.get<Article>("article")
                article?.let {
                    DetailScreen(article = article) {
                        navController.navigateUp()
                    }
                }
            }

            composable(route = Routes.SearchScreen.route) {
                SearchScreen {
                    navigateToDetail(navController, it)
                }
            }
            composable(route = Routes.BookmarkScreen.route) {
                BookmarkScreen {
                    navigateToDetail(navController, it)
                }
            }
        }
    }

}


private fun navigateToTab(navController: NavHostController, route: String) {
    navController.navigate(route) {
        navController.graph.startDestinationRoute?.let { homeScreen ->
            popUpTo(homeScreen) {// امسح كل الشاشات اللي فوق route في stack
                saveState = true
            }
            launchSingleTop = true // متفتحش نسخة جديدة لو الشاشة دي هي اللي فوق فعلاً
            restoreState = true //لما ترجع للشاشة، رجع حالتها زي ما كانت
        }
    }
}

private fun navigateToDetail(navController: NavHostController, article: Article) {
    navController.currentBackStackEntry?.savedStateHandle?.set("article", article)
    navController.navigate(route = Routes.DetailScreen.route)
}