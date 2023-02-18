package com.swaptech.vkservicestask.presentation.screens.services

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.swaptech.vkservicestask.R
import com.swaptech.vkservicestask.domain.service.Service
import com.swaptech.vkservicestask.presentation.util.ui.ErrorPlaceHolder
import com.swaptech.vkservicestask.presentation.util.ui.FatalErrorPlaceholder
import com.swaptech.vkservicestask.presentation.util.ui.LoadingPlaceholder
import com.swaptech.vkservicestask.presentation.util.ui.ServiceItem

@Composable
fun ServicesScreen(
    onServiceItemClick: (Service) -> Unit,
    viewModel: ServicesScreenViewModel
) {
    val uiState = viewModel.uiState
    LaunchedEffect(Unit) {
        viewModel.getServices()
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(R.string.vk_services))
                },
                actions = {
                    IconButton(
                        onClick = viewModel::getServices
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Refresh,
                            contentDescription = null
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        when {
            uiState.isSuccess -> {
                Column(
                    modifier = Modifier
                        .padding(innerPadding)
                        .fillMaxSize()
                ) {
                    LazyColumn {
                        items(uiState.services) { item ->
                            ServiceItem(
                                onClick = {
                                    onServiceItemClick(item)
                                },
                                service = item
                            )
                        }
                    }
                }
            }
            uiState.isError -> {
                ErrorPlaceHolder()
            }
            uiState.isFatalError -> {
                FatalErrorPlaceholder()
            }
            else -> {
                LoadingPlaceholder()
            }
        }
    }
}
