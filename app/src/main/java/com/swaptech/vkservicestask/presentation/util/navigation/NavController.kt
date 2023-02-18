package com.swaptech.vkservicestask.presentation.util.navigation

import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue

class NavController(
    private val controllerId: String,
    private val startDestinationRoute: String
) {

    var currentDestination: Destination? by mutableStateOf(null)
        private set

    private var destinations: Map<String, Destination> by mutableStateOf(mapOf())

    private val _backStack: MutableList<Destination?> = mutableStateListOf()

    fun addDestination(destination: Destination) {
        if (currentDestination == null) {
            currentDestination = destination
        }
        if (destinations[destination.route] == null) {
            destinations = destinations.toMutableMap().apply {
                this[destination.route] = destination
            }
        }
    }

    fun navigate(route: String, navArgs: Bundle? = null) {
        destinations[route]?.let { destination ->
            _backStack.add(currentDestination)
            currentDestination = destination.copy(
                navArgs = navArgs
            )
        }
    }

    fun back(): Boolean {
        if (_backStack.isNotEmpty()) {
            _backStack.removeLast()
            currentDestination = if (_backStack.isNotEmpty()) {
                _backStack.last()
            } else {
                destinations[startDestinationRoute]
            }
            return true
        }
        return false
    }

    companion object {
        private val controllers = mutableMapOf<String, NavController>()

        fun Saver() = Saver<NavController, String>(
            save = { navController ->
                controllers[navController.controllerId] = navController
                navController.controllerId
            },
            restore = { id ->
                controllers[id] ?: throw Exception(
                    "Unable to restore NavController with id: $id"
                )
            }
        )
    }
}


@Composable
fun rememberNavController(
    controllerId: String,
    startDestination: String
): NavController {
    return rememberSaveable(
        saver = NavController.Saver()
    ) {
        NavController(controllerId, startDestination)
    }
}
