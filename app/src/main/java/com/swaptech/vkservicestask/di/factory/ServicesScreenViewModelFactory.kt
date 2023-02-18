package com.swaptech.vkservicestask.di.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.swaptech.vkservicestask.domain.service.GetServicesUseCase
import com.swaptech.vkservicestask.presentation.screens.services.ServicesScreenViewModel

class ServicesScreenViewModelFactory(
    private val getServicesUseCase: GetServicesUseCase
): ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ServicesScreenViewModel(
            getServicesUseCase = getServicesUseCase
        ) as T
    }
}
