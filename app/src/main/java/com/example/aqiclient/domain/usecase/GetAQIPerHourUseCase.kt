package com.example.aqiclient.domain.usecase

import com.example.aqiclient.data.model.APIResponse
import com.example.aqiclient.data.util.Resource
import com.example.aqiclient.domain.repository.AQIPerHourRepository

class GetAQIPerHourUseCase(private val aqiPerHourRepository: AQIPerHourRepository) {

    suspend fun execute(): Resource<APIResponse> {
        return aqiPerHourRepository.getAQIPerHour()
    }
}