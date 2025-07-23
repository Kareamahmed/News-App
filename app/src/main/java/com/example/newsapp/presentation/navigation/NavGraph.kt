package com.example.newsapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.newsapp.presentation.screens.home.HomeScreen
import com.example.newsapp.presentation.screens.onbording.OnBoardingScreen
import com.example.newsapp.presentation.screens.onbording.viewmodel.OnBoardingViewModel
import com.example.newsapp.presentation.screens.search.SearchScreen

@Composable
fun NavGraph(startDestination: String) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = startDestination) {
        navigation(
            route = Routes.AppStartNavigation.route,
            startDestination = Routes.OnBoardingScreen.route
        ) {
            composable(route = Routes.OnBoardingScreen.route) {
                val viewModel: OnBoardingViewModel = hiltViewModel()
                OnBoardingScreen(event = viewModel::onEvent)
            }
        }
        navigation(
            route = Routes.NewsNavigation.route,
            startDestination = Routes.NewsNavigatorScreen.route
        ) {
            composable(route = Routes.NewsNavigatorScreen.route) {
                HomeScreen {
                    navController.navigate(route = Routes.SearchScreen.route)
                }
            }
            composable(route =Routes.SearchScreen.route){
                SearchScreen()
            }
        }
    }
}