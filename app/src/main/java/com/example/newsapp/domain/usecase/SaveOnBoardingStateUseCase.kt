package com.example.newsapp.domain.usecase

import com.example.newsapp.domain.manger.LocalUserManger

class SaveOnBoardingStateUseCase(
    private val localUserManger: LocalUserManger
) {
    suspend operator fun invoke(){
        localUserManger.saveOnboardingState()
    }
}