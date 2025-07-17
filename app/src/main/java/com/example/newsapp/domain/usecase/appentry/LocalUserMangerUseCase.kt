package com.example.newsapp.domain.usecase.appentry

data class LocalUserMangerUseCase(
    val readOnBoardingStateUseCase: ReadOnBoardingStateUseCase,
    val saveOnBoardingStateUseCase: SaveOnBoardingStateUseCase
)
