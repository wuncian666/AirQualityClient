package com.example.aqiclient.presentation.di

import com.example.aqiclient.domain.repository.AQIPerHourRepository
import com.example.aqiclient.domain.usecase.GetAQIPerHourUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Singleton
    @Provides
    fun provideGetAQIPerHourUseCase(aqiPerHourRepository: AQIPerHourRepository): GetAQIPerHourUseCase {
        return GetAQIPerHourUseCase(aqiPerHourRepository)
    }
}