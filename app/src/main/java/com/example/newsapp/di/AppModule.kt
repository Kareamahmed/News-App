package com.example.newsapp.di

import android.content.Context
import com.example.newsapp.data.local.datastore.LocalUserMangerImpl
import com.example.newsapp.domain.manger.LocalUserManger
import com.example.newsapp.domain.usecase.appentry.LocalUserMangerUseCase
import com.example.newsapp.domain.usecase.appentry.ReadOnBoardingStateUseCase
import com.example.newsapp.domain.usecase.appentry.SaveOnBoardingStateUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideLocalUserManger(
        @ApplicationContext
        context: Context,
    ):LocalUserManger = LocalUserMangerImpl(context)

    @Provides
    @Singleton
    fun provideLocalUserMangerUseCase(
        localUserManger: LocalUserManger,
    ) = LocalUserMangerUseCase(
        readOnBoardingStateUseCase = ReadOnBoardingStateUseCase(localUserManger),
        saveOnBoardingStateUseCase = SaveOnBoardingStateUseCase(localUserManger)
    )

}