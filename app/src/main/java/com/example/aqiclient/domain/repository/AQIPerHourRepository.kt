package com.example.aqiclient.domain.repository

import com.example.aqiclient.data.model.APIResponse
import com.example.aqiclient.data.util.Resource

interface AQIPerHourRepository {

    suspend fun getAQIPerHour(): Resource<APIResponse>
}