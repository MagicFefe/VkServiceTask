package com.swaptech.vkservicestask.data.service

import com.swaptech.vkservicestask.domain.service.ServiceResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class ServiceRepository(
    private val serviceApi: ServiceApi
) {

    suspend fun getServices(): Response<ServiceResponse> = withContext(Dispatchers.IO) {
        serviceApi.getServices()
    }
}