package com.swaptech.vkservicestask.presentation.util.common

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.swaptech.vkservicestask.di.container.DataContainer
import com.swaptech.vkservicestask.di.container.DomainContainer
import com.swaptech.vkservicestask.di.container.PresentationContainer
import com.swaptech.vkservicestask.di.factory.ServicesScreenViewModelFactory

@Composable
fun rememberServicesScreenViewModelFactory(): ServicesScreenViewModelFactory {
    return remember {
        PresentationContainer.getServiceScreenViewModelFactory(
            DomainContainer.getGetServicesUseCase(
                DataContainer.getServiceRepository(
                    DataContainer.getServiceApi(
                        DataContainer.getRetrofitClient()
                    )
                )
            )
        )
    }
}

@Composable
fun <T: ViewModel> viewModel(
    viewModelFactory: ViewModelProvider.Factory,
    modelClass: Class<out ViewModel>
): T {
    @Suppress("UNCHECKED_CAST")
    return remember {
        viewModelFactory.create(modelClass) as T
    }
}
