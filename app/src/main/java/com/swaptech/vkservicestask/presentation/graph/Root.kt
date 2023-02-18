package com.swaptech.vkservicestask.presentation.graph

sealed class Root(val route: String) {

    object Services : Root("services")

    object Detail : Root("detail")
}
