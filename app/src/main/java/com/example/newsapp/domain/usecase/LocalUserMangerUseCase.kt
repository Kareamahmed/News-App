package com.example.newsapp.domain.usecase

data class LocalUserMangerUseCase(
    val readOnBoardingStateUseCase: ReadOnBoardingStateUseCase,
    val saveOnBoardingStateUseCase: SaveOnBoardingStateUseCase
)
