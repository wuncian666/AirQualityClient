package com.example.aqiclient.presentation.viewmodel

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.aqiclient.data.model.APIResponse
import com.example.aqiclient.data.util.Resource
import com.example.aqiclient.domain.usecase.GetAQIPerHourUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AQIPerHourViewModel(
    private val app: Application,
    private val getAQIPerHourUseCase: GetAQIPerHourUseCase
) : AndroidViewModel(app) {
    val aqiPerHour: MutableLiveData<Resource<APIResponse>> = MutableLiveData()

    fun getAQIPerHour() = viewModelScope.launch(Dispatchers.IO) {
        aqiPerHour.postValue(Resource.Loading())
        try {
            if (isNetworkAvailable(app)) {
                val apiResult = getAQIPerHourUseCase.execute()
                aqiPerHour.postValue(apiResult)
            } else {
                aqiPerHour.postValue(Resource.Error("Internet is not available"))
            }
        } catch (e: Exception) {
            aqiPerHour.postValue(Resource.Error(e.message.toString()))
        }
    }

    private fun isNetworkAvailable(context: Context?): Boolean {
        if (context == null) return false
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val capabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
                    ?: return false
            if (capabilities != null) {
                when {
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> return true
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> return true
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> return true
                }
            } else {
                val activeNetworkInfo = connectivityManager.activeNetworkInfo
                if (activeNetworkInfo != null && activeNetworkInfo.isConnected) return true
            }
        }
        return false
    }
}