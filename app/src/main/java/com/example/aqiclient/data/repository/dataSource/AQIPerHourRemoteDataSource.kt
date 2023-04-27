package com.example.aqiclient.data.repository.dataSource

import com.example.aqiclient.data.model.APIResponse
import retrofit2.Response

interface AQIPerHourRemoteDataSource {
    suspend fun getAQIPerHour(): Response<APIResponse>
}