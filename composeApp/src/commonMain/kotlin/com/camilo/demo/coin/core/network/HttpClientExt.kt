package com.camilo.demo.coin.core.network

import com.camilo.demo.coin.core.domain.DataError
import com.camilo.demo.coin.core.logging.AppLogger
import io.ktor.client.call.body
import io.ktor.client.network.sockets.SocketTimeoutException
import io.ktor.client.statement.HttpResponse
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.coroutines.ensureActive
import kotlin.coroutines.coroutineContext
import com.camilo.demo.coin.core.domain.Result


suspend inline fun <reified T> safeCall(
    execute: () -> HttpResponse
): Result<T, DataError.Remote> {
    AppLogger.d("Starting network request", tag = "Network")
    
    val response = try {
        execute()
    } catch (e: SocketTimeoutException) {
        AppLogger.e("Request timeout", e, "Network")
        return Result.Error(DataError.Remote.REQUEST_TIMEOUT)
    } catch (e: UnresolvedAddressException) {
        AppLogger.e("No internet connection", e, "Network")
        return Result.Error(DataError.Remote.NO_INTERNET_CONNECTION)
    } catch (e: Exception) {
        coroutineContext.ensureActive()
        AppLogger.e("Unknown error occurred", e, "Network")
        return Result.Error(DataError.Remote.UNKNOWN_ERROR)
    }
    
    AppLogger.d("Received response with status: ${response.status.value}", tag = "Network")
    return responseToResult(response)
}

suspend inline fun <reified T> responseToResult(response: HttpResponse): Result<T, DataError.Remote> {
    return when (response.status.value) {
        in 200..299 -> {
            try {
                val result = Result.Success(response.body<T>())
                AppLogger.d("Successfully deserialized response body", tag = "Network")
                result
            } catch (e: Exception) {
                AppLogger.e("Failed to deserialize response body", e, "Network")
                return Result.Error(DataError.Remote.SERIALIZATION_ERROR)
            }
        }
        408 -> {
            AppLogger.w("Request timeout (408)", tag = "Network")
            Result.Error(DataError.Remote.REQUEST_TIMEOUT)
        }
        429 -> {
            AppLogger.w("Too many requests (429)", tag = "Network")
            Result.Error(DataError.Remote.TOO_MANY_REQUESTS)
        }
        in 500 ..599 -> {
            AppLogger.e("Server error (${response.status.value})", tag = "Network")
            Result.Error(DataError.Remote.SERVER)
        }
        else -> {
            AppLogger.e("Unknown error (${response.status.value})", tag = "Network")
            Result.Error(DataError.Remote.UNKNOWN_ERROR)
        }
    }
}