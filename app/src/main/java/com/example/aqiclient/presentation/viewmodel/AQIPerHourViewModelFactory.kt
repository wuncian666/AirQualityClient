package com.example.aqiclient.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.aqiclient.domain.usecase.GetAQIPerHourUseCase

class AQIPerHourViewModelFactory(
    private val app: Application,
    private val getAQIPerHourUseCase: GetAQIPerHourUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AQIPerHourViewModel(
            app,
            getAQIPerHourUseCase
        ) as T
    }
}