package com.swaptech.vkservicestask.presentation.screens.root

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.core.os.bundleOf
import com.swaptech.vkservicestask.presentation.graph.Root
import com.swaptech.vkservicestask.presentation.screens.detail.DetailScreen
import com.swaptech.vkservicestask.presentation.screens.services.ServicesScreen
import com.swaptech.vkservicestask.presentation.screens.services.ServicesScreenViewModel
import com.swaptech.vkservicestask.presentation.util.config.ROOT_NAV_CONTROLLER_ID
import com.swaptech.vkservicestask.presentation.util.config.SERVICE_DESCRIPTION_KEY
import com.swaptech.vkservicestask.presentation.util.config.SERVICE_ICON_URL_KEY
import com.swaptech.vkservicestask.presentation.util.config.SERVICE_NAME_KEY
import com.swaptech.vkservicestask.presentation.util.config.SERVICE_URL_KEY
import com.swaptech.vkservicestask.presentation.util.common.rememberServicesScreenViewModelFactory
import com.swaptech.vkservicestask.presentation.util.navigation.Destination
import com.swaptech.vkservicestask.presentation.util.navigation.NavHost
import com.swaptech.vkservicestask.presentation.util.navigation.rememberNavController
import com.swaptech.vkservicestask.presentation.util.common.viewModel

@Composable
fun RootScreen() {
    val navController = rememberNavController(
        controllerId = ROOT_NAV_CONTROLLER_ID,
        startDestination = Root.Services.route
    ).also { navController ->
        navController.addDestination(
            Destination(
                route = Root.Services.route,
                content = {
                    val factory = rememberServicesScreenViewModelFactory()
                    ServicesScreen(
                        onServiceItemClick = { clickedService ->
                            val bundle = bundleOf(
                                SERVICE_NAME_KEY to clickedService.name,
                                SERVICE_DESCRIPTION_KEY to clickedService.description,
                                SERVICE_ICON_URL_KEY to clickedService.iconUrl,
                                SERVICE_URL_KEY to clickedService.serviceUrl,
                            )
                            navController.navigate(Root.Detail.route, bundle)
                        },
                        viewModel = viewModel(
                            viewModelFactory = factory,
                            modelClass = ServicesScreenViewModel::class.java
                        )
                    )
                }
            )
        )
        navController.addDestination(
            Destination(
                route = Root.Detail.route,
                content = {
                    navController.currentDestination?.let { destination ->
                        val serviceName = destination.navArgs?.getString(SERVICE_NAME_KEY)
                        val serviceDescription =
                            destination.navArgs?.getString(SERVICE_DESCRIPTION_KEY)
                        val serviceIconUrl = destination.navArgs?.getString(SERVICE_ICON_URL_KEY)
                        val serviceUrl = destination.navArgs?.getString(SERVICE_URL_KEY)

                        checkNotNull(serviceName)
                        checkNotNull(serviceDescription)
                        checkNotNull(serviceIconUrl)
                        checkNotNull(serviceUrl)

                        DetailScreen(
                            serviceName = serviceName,
                            serviceDescription = serviceDescription,
                            serviceIconUrl = serviceIconUrl,
                            serviceUrl = serviceUrl,
                            onBackNavButtonClicked = {
                                navController.back()
                            }
                        )
                    }
                }
            )
        )
    }
    Scaffold { innerPadding ->
        NavHost(
            modifier = Modifier.padding(innerPadding),
            navController = navController
        )
    }
}
