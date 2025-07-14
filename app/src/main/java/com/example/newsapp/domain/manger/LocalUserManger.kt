package com.example.newsapp.domain.manger

import kotlinx.coroutines.flow.Flow

interface LocalUserManger {

    suspend fun saveOnboardingState()

    fun readOnBoardingState():Flow<Boolean>
}