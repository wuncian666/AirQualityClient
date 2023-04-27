package com.example.aqiclient.data.api


import com.example.aqiclient.BuildConfig
import com.example.aqiclient.data.model.APIResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface AQIPerHourAPIService {
    @GET("aqx_p_432")
    suspend fun getAQIPerHour(
        @Query("api_key")
        apiKey: String = BuildConfig.AQI_KEY
    ): Response<APIResponse>
}