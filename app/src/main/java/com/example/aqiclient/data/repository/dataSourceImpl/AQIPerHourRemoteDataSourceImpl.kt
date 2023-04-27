package com.example.aqiclient.data.repository.dataSourceImpl

import com.example.aqiclient.data.api.AQIPerHourAPIService
import com.example.aqiclient.data.model.APIResponse
import com.example.aqiclient.data.repository.dataSource.AQIPerHourRemoteDataSource
import retrofit2.Response

class AQIPerHourRemoteDataSourceImpl(
    private val aqiPerHourAPIService: AQIPerHourAPIService
) : AQIPerHourRemoteDataSource {
    override suspend fun getAQIPerHour(): Response<APIResponse> {
        return aqiPerHourAPIService.getAQIPerHour()
    }
}