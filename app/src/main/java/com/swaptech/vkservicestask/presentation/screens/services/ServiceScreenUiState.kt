package com.swaptech.vkservicestask.presentation.screens.services

import com.swaptech.vkservicestask.domain.service.Service

data class ServiceScreenUiState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val isFatalError: Boolean = false,
    val isSuccess: Boolean = false,
    val services: List<Service> = emptyList()
)
