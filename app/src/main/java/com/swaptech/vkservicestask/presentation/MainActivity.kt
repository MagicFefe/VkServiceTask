package com.swaptech.vkservicestask.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.swaptech.vkservicestask.presentation.screens.root.RootScreen
import com.swaptech.vkservicestask.presentation.theme.VKServicesTaskTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VKServicesTaskTheme {
                RootScreen()
            }
        }
    }
}

