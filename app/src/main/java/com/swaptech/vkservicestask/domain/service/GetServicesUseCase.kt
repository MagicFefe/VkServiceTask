package com.swaptech.vkservicestask.domain.service

import com.swaptech.vkservicestask.data.service.ServiceRepository
import retrofit2.Response

class GetServicesUseCase(private val serviceRepository: ServiceRepository) {

    suspend operator fun invoke(): Response<ServiceResponse> = serviceRepository.getServices()
}
