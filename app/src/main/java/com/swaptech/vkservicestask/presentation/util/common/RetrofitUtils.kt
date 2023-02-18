package com.swaptech.vkservicestask.presentation.util.common

import retrofit2.Response


suspend fun <T> executeNetworkRequest(
    action: suspend () -> Response<T>,
    onSuccess: (T) -> Unit,
    onError: (String) -> Unit,
    onFatalError: (Throwable) -> Unit
) {
    try {
        val response = action()
        response.body()?.let { body ->
            onSuccess(body)
        } ?: run {
            response.errorBody()?.let { errorBody ->
                onError(errorBody.string())
            } ?: run {
                onError(response.message())
            }
        }
    } catch (e: Exception) {
        onFatalError(e)
    }
}
