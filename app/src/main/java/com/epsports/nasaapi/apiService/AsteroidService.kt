package com.epsports.nasaapi.apiService

import com.epsports.nasaapi.utils.Constant.API_KEY
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface AsteroidService {
    @GET("neo/rest/v1/feed")
    suspend fun getNeoFeed(@Query("api_key") apiKey: String = API_KEY): Response<String>
}

object AsteroidNetwork{
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.nasa.gov/")
        .addConverterFactory(ScalarsConverterFactory.create())
        .build()

    val nasaService: AsteroidService = retrofit.create(AsteroidService::class.java)
}