package com.example.aqiclient.presentation.di

import com.example.aqiclient.data.repository.AQIPerHourRepositoryImpl
import com.example.aqiclient.data.repository.dataSource.AQIPerHourRemoteDataSource
import com.example.aqiclient.domain.repository.AQIPerHourRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun provideAQIPerHourRepository(
        aqiPerHourRemoteDataSource: AQIPerHourRemoteDataSource
    ): AQIPerHourRepository {
        return AQIPerHourRepositoryImpl(aqiPerHourRemoteDataSource)
    }
}