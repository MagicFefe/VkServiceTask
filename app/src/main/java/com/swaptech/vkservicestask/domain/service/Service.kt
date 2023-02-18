package com.swaptech.vkservicestask.domain.service

import com.google.gson.annotations.SerializedName

data class Service(
    val name: String,
    val description: String,
    @SerializedName("icon_url")
    val iconUrl: String,
    @SerializedName("service_url")
    val serviceUrl: String
)
