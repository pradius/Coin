package com.camilo.demo.coin.core.domain

sealed interface DataError: Error {
    enum class Remote: DataError {
        REQUEST_TIMEOUT,
        TOO_MANY_REQUESTS,
        NO_INTERNET_CONNECTION,
        UNKNOWN_ERROR
    }

    enum class Local: DataError {
        DISK_ERROR,
        FILE_NOT_FOUND,
        FILE_ALREADY_EXISTS,
        INSUFFICIENT_FUNDS,
        DATABASE_ERROR,
        UNKNOWN_ERROR
    }
}