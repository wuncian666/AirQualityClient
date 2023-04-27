package com.example.aqiclient.presentation.di

import android.app.Application
import com.example.aqiclient.domain.usecase.GetAQIPerHourUseCase
import com.example.aqiclient.presentation.viewmodel.AQIPerHourViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FactoryModule {
    @Singleton
    @Provides
    fun provideAQIPerHourViewModelFactory(
        application: Application,
        getAQIPerHourUseCase: GetAQIPerHourUseCase
    ): AQIPerHourViewModelFactory {
        return AQIPerHourViewModelFactory(
            application,
            getAQIPerHourUseCase
        )
    }
}