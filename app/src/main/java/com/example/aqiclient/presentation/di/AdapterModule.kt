package com.example.aqiclient.presentation.di

import com.example.aqiclient.presentation.adapter.AQIPerHourAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AdapterModule {

    @Singleton
    @Provides
    fun provideAQIPerHourAdapter(): AQIPerHourAdapter {
        return AQIPerHourAdapter()
    }
}