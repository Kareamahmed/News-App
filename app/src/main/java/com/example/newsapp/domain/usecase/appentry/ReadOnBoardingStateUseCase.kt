package com.example.newsapp.domain.usecase.appentry

import com.example.newsapp.domain.manger.LocalUserManger
import kotlinx.coroutines.flow.Flow

class ReadOnBoardingStateUseCase(
    private val localUserManger: LocalUserManger,
) {
    operator fun invoke(): Flow<Boolean> {
       return localUserManger.readOnBoardingState()
    }
}