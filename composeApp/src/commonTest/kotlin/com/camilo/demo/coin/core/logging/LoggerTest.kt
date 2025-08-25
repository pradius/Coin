package com.camilo.demo.coin.core.logging

import co.touchlab.kermit.Logger
import kotlin.test.Test
import kotlin.test.assertTrue

class LoggerTest {

    @Test
    fun testKermitLoggerInitialization() {
        // Test that we can create and use the AppLogger
        try {
            AppLogger.i("Test message from unit test", tag = "Test")
            AppLogger.d("Debug message", tag = "Test")
            AppLogger.w("Warning message", tag = "Test")
            AppLogger.e("Error message", tag = "Test")
            AppLogger.v("Verbose message", tag = "Test")
            
            // If we get here without exception, logging is working
            assertTrue(true, "Kermit logging initialized successfully")
        } catch (e: Exception) {
            assertTrue(false, "Failed to initialize Kermit logging: ${e.message}")
        }
    }

    @Test
    fun testLoggerWithException() {
        val testException = RuntimeException("Test exception")
        try {
            AppLogger.e("Error with exception", testException, "Test")
            assertTrue(true, "Exception logging works")
        } catch (e: Exception) {
            assertTrue(false, "Failed to log with exception: ${e.message}")
        }
    }
}