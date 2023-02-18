package com.swaptech.vkservicestask

import com.swaptech.vkservicestask.di.container.DataContainer
import com.swaptech.vkservicestask.di.container.DomainContainer
import com.swaptech.vkservicestask.di.container.PresentationContainer
import com.swaptech.vkservicestask.presentation.screens.services.ServicesScreenViewModel
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * Представлен тест только для вью модели, так как использование сторонних
 * библиотек кроме работы с сетью, загрузки изображений и парсинга JSON запрещены
 */
class ViewModelTest {
    private val servicesScreenViewModel = PresentationContainer.getServiceScreenViewModelFactory(
        DomainContainer.getGetServicesUseCase(
            DataContainer.getServiceRepository(
                DataContainer.getServiceApi(
                    DataContainer.getRetrofitClient()
                )
            )
        )
    ).create(ServicesScreenViewModel::class.java)

    @Test
    fun checkThatUiStateHasDefaultValues() {
        assertFalse(servicesScreenViewModel.uiState.isSuccess)
        assertFalse(servicesScreenViewModel.uiState.isError)
        assertFalse(servicesScreenViewModel.uiState.isFatalError)
        assertFalse(servicesScreenViewModel.uiState.isLoading)
        assertTrue(servicesScreenViewModel.uiState.services.isEmpty())
    }

    @Test
    fun checkThatGetServicesReturnsUnit() {
        assertEquals(servicesScreenViewModel.getServices(), Unit)
    }
}
