package com.epsports.nasaapi.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.epsports.nasaapi.apiService.AsteroidNetwork
import com.epsports.nasaapi.apiService.Service
import com.epsports.nasaapi.model.AsteroidData
import com.epsports.nasaapi.model.ResponsePictureOfTheDay
import com.epsports.nasaapi.utils.asteroidJsonObject
import kotlinx.coroutines.launch
import org.json.JSONObject

class HomeViewModel : ViewModel() {
    private val _pictureOfTheDay = MutableLiveData<ResponsePictureOfTheDay>()
    val pictureOfTheDay: MutableLiveData<ResponsePictureOfTheDay> = _pictureOfTheDay

    init {
        getPictureOfTheDay()
        getAsteroidData()
    }

    private fun getPictureOfTheDay() {
        viewModelScope.launch {
            val response = Service.nasaService.getPictureOfTheDay("WJrfpaXgvTEEpzx5yCjuNanmejvlg9EHbdzi5xsn")
            _pictureOfTheDay.value = response.body()
        }
    }

    private val _asteroidData = MutableLiveData<ArrayList<AsteroidData>>()
    val asteroidData: LiveData<ArrayList<AsteroidData>> get() = _asteroidData

    private fun getAsteroidData(){
        viewModelScope.launch {
            try {
                val response = AsteroidNetwork.nasaService.getNeoFeed()
                if (response.isSuccessful){
                    response.body()?.let {
                        val jsonObject = JSONObject(it)
                        val asteroidJsonObject = asteroidJsonObject(jsonObject)
                        _asteroidData.value = asteroidJsonObject
                    }
                }
            }
            catch (e: Exception){
                Log.d("TAG", "getAsteroidData: $e")
            }
        }
    }

}