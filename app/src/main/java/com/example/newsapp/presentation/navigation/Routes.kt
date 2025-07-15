package com.example.newsapp.presentation.navigation

sealed class Routes(val route: String) {
    object OnBoardingScreen : Routes(route = "onBoardingScreen")
    object HomeScreen : Routes(route = "homeScreen")
    object DetailScreen : Routes(route = "detailScreen")
    object SearchScreen : Routes(route = "searchScreen")
    object BookmarkScreen : Routes(route = "bookmarkScreen")

    object AppStartNavigation : Routes(route = "appStartNavigation")
    object NewsNavigation : Routes(route = "newsNavigation")
    object NewsNavigatorScreen : Routes(route = "newsNavigatorScreen")
}