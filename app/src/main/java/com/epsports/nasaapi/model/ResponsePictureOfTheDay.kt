package com.epsports.nasaapi.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class ResponsePictureOfTheDay(
    @SerializedName("date")
    val date: String? = null,
    @SerializedName("explanation")
    val explanation: String? = null,
    @SerializedName("media_type")
    val mediaType: String? = null,
    @SerializedName("service_version")
    val serviceVersion: String? = null,
    @SerializedName("title")
    val title: String? = null,
    @SerializedName("url")
    val url: String? = null
)

