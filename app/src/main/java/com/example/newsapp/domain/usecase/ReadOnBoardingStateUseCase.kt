package com.example.newsapp.domain.usecase

import com.example.newsapp.domain.manger.LocalUserManger

class ReadOnBoardingStateUseCase(
    private val localUserManger: LocalUserManger,
) {
    operator fun invoke() {
        localUserManger.readOnBoardingState()
    }
}