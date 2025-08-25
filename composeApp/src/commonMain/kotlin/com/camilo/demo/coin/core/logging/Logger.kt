package com.camilo.demo.coin.core.logging

import co.touchlab.kermit.Logger
import co.touchlab.kermit.Severity
import co.touchlab.kermit.StaticConfig
import co.touchlab.kermit.platformLogWriter

object AppLogger {
    
    private val logger = Logger(
        config = StaticConfig(
            logWriterList = listOf(platformLogWriter())
        ),
        tag = "CoinApp"
    )
    
    fun d(message: String, throwable: Throwable? = null, tag: String? = null) {
        logger.d(throwable) { "[${tag ?: "DEBUG"}] $message" }
    }
    
    fun i(message: String, throwable: Throwable? = null, tag: String? = null) {
        logger.i(throwable) { "[${tag ?: "INFO"}] $message" }
    }
    
    fun w(message: String, throwable: Throwable? = null, tag: String? = null) {
        logger.w(throwable) { "[${tag ?: "WARN"}] $message" }
    }
    
    fun e(message: String, throwable: Throwable? = null, tag: String? = null) {
        logger.e(throwable) { "[${tag ?: "ERROR"}] $message" }
    }
    
    fun v(message: String, throwable: Throwable? = null, tag: String? = null) {
        logger.v(throwable) { "[${tag ?: "VERBOSE"}] $message" }
    }
}