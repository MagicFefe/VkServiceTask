package com.swaptech.vkservicestask.presentation.util.navigation

import android.os.Bundle
import androidx.compose.runtime.Composable

data class Destination(
    val route: String,
    val navArgs: Bundle? = null,
    val content: @Composable () -> Unit
)
