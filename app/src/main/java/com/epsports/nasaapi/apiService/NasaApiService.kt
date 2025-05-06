package com.epsports.nasaapi.apiService

import com.epsports.nasaapi.model.ResponsePictureOfTheDay
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface NasaApiService {
    @GET("planetary/apod")
    suspend fun getPictureOfTheDay(
        @retrofit2.http.Query("api_key") apiKey: String
    ): Response<ResponsePictureOfTheDay>
}

object Service {
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://api.nasa.gov/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val nasaService: NasaApiService = retrofit.create(NasaApiService::class.java)
}