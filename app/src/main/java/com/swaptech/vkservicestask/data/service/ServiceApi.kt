package com.swaptech.vkservicestask.data.service

import com.swaptech.vkservicestask.domain.service.ServiceResponse
import retrofit2.Response
import retrofit2.http.GET

interface ServiceApi {

    @GET("/semi-final-data.json")
    suspend fun getServices(): Response<ServiceResponse>
}
