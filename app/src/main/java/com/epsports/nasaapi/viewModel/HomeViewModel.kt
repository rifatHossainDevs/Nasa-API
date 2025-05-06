package com.epsports.nasaapi.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.epsports.nasaapi.apiService.Service
import com.epsports.nasaapi.model.ResponsePictureOfTheDay
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    private val _pictureOfTheDay = MutableLiveData<ResponsePictureOfTheDay>()
    val pictureOfTheDay: MutableLiveData<ResponsePictureOfTheDay> = _pictureOfTheDay

    init {
        getPictureOfTheDay()
    }

    private fun getPictureOfTheDay() {
        viewModelScope.launch {
            val response = Service.nasaService.getPictureOfTheDay("WJrfpaXgvTEEpzx5yCjuNanmejvlg9EHbdzi5xsn")
            _pictureOfTheDay.value = response.body()
        }
    }

}