package com.example.newsapp.presentation.onbording

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.domain.usecase.appentry.LocalUserMangerUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    private val localUserMangerUseCase: LocalUserMangerUseCase,
) : ViewModel() {

    fun onEvent(event: OnBoardingEvent) {
        when (event) {
            is OnBoardingEvent.SaveAppEntry -> {
                saveAppEntry()
            }
        }
    }

    private fun saveAppEntry() {
        viewModelScope.launch {
            localUserMangerUseCase.saveOnBoardingStateUseCase()
        }
    }
}