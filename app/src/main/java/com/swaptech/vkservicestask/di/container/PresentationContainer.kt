package com.swaptech.vkservicestask.di.container

import com.swaptech.vkservicestask.di.factory.ServicesScreenViewModelFactory
import com.swaptech.vkservicestask.domain.service.GetServicesUseCase

object PresentationContainer {

    fun getServiceScreenViewModelFactory(
        getServicesUseCase: GetServicesUseCase
    ) = ServicesScreenViewModelFactory(getServicesUseCase)
}
