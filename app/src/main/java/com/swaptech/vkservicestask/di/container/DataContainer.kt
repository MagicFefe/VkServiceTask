package com.swaptech.vkservicestask.di.container

import com.swaptech.vkservicestask.data.service.ServiceApi
import com.swaptech.vkservicestask.data.service.ServiceRepository
import com.swaptech.vkservicestask.presentation.util.config.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object DataContainer {

    fun getRetrofitClient(): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getServiceApi(retrofit: Retrofit): ServiceApi =
        retrofit.create(ServiceApi::class.java)

    fun getServiceRepository(serviceApi: ServiceApi): ServiceRepository =
        ServiceRepository(serviceApi)
}
