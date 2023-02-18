package com.swaptech.vkservicestask.di.container

import com.swaptech.vkservicestask.data.service.ServiceRepository
import com.swaptech.vkservicestask.domain.service.GetServicesUseCase

object DomainContainer {

    fun getGetServicesUseCase(serviceRepository: ServiceRepository) =
        GetServicesUseCase(serviceRepository)
}
