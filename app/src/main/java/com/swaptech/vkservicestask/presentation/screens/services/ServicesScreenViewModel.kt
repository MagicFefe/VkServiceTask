package com.swaptech.vkservicestask.presentation.screens.services

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.swaptech.vkservicestask.domain.service.GetServicesUseCase
import com.swaptech.vkservicestask.presentation.util.common.executeNetworkRequest
import kotlinx.coroutines.launch

class ServicesScreenViewModel(
    private val getServicesUseCase: GetServicesUseCase
) : ViewModel() {

    var uiState by mutableStateOf(ServiceScreenUiState())
        private set

    fun getServices() {
        uiState = uiState.copy(
            isSuccess = false,
            isError = false,
            isFatalError = false,
            isLoading = true
        )
        viewModelScope.launch {
            executeNetworkRequest(
                action = { getServicesUseCase() },
                onSuccess = { result ->
                    uiState = uiState.copy(
                        isSuccess = true,
                        isError = false,
                        isFatalError = false,
                        services = result.items
                    )
                },
                onError = {
                    uiState = uiState.copy(
                        isSuccess = false,
                        isError = false,
                        isFatalError = false,
                        services = emptyList()
                    )
                },
                onFatalError = {
                    uiState = uiState.copy(
                        isSuccess = false,
                        isError = false,
                        isFatalError = true,
                        services = emptyList()
                    )
                }
            )
        }
        uiState = uiState.copy(
            isLoading = false
        )
    }
}
