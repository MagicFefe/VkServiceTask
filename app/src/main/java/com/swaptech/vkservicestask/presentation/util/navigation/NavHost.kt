package com.swaptech.vkservicestask.presentation.util.navigation

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.swaptech.vkservicestask.presentation.util.ext.findActivity

@Composable
fun NavHost(
    modifier: Modifier = Modifier,
    navController: NavController,
) {
    val context = LocalContext.current
    Column(
        modifier = modifier
    ) {
        navController.currentDestination?.content?.invoke()
    }
    BackHandler(
        onBack = {
            if (!navController.back()) {
                context.findActivity().finish()
            } else {
                navController.back()
            }
        }
    )
}
