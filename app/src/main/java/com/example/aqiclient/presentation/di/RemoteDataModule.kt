package com.example.aqiclient.presentation.di

import com.example.aqiclient.data.api.AQIPerHourAPIService
import com.example.aqiclient.data.repository.dataSource.AQIPerHourRemoteDataSource
import com.example.aqiclient.data.repository.dataSourceImpl.AQIPerHourRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RemoteDataModule {

    @Singleton
    @Provides
    fun provideAQIPerHourRemoteDataSource(aqiPerHourAPIService: AQIPerHourAPIService): AQIPerHourRemoteDataSource {
        return AQIPerHourRemoteDataSourceImpl(aqiPerHourAPIService)
    }
}