package com.example.newsapp

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.domain.usecase.appentry.LocalUserMangerUseCase
import com.example.newsapp.presentation.navigation.Routes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    localUserMangerUseCase: LocalUserMangerUseCase,
) : ViewModel() {
    var splashCondition by mutableStateOf(true)
        private set
    var startDestination by mutableStateOf(Routes.AppStartNavigation.route)
        private set

    init {
        localUserMangerUseCase.readOnBoardingStateUseCase().onEach { isTrue ->
            startDestination = if (isTrue)
                Routes.NewsNavigation.route
            else
                Routes.AppStartNavigation.route

            delay(timeMillis = 300)
            splashCondition = false
        }.launchIn(viewModelScope)
    }
}