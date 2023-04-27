package com.example.aqiclient.data.repository

import com.example.aqiclient.data.model.APIResponse
import com.example.aqiclient.data.repository.dataSource.AQIPerHourRemoteDataSource
import com.example.aqiclient.data.util.Resource
import com.example.aqiclient.domain.repository.AQIPerHourRepository
import retrofit2.Response

class AQIPerHourRepositoryImpl(
    private val aqiPerHourRemoteDataSource: AQIPerHourRemoteDataSource
) : AQIPerHourRepository {
    override suspend fun getAQIPerHour(): Resource<APIResponse> {
        return responseToResource(aqiPerHourRemoteDataSource.getAQIPerHour())
    }

    private fun responseToResource(response: Response<APIResponse>): Resource<APIResponse> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }
}