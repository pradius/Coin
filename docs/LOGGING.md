# Logging

This project uses [Kermit](https://github.com/touchlab/Kermit) from Touchlabs for multiplatform logging.

## Usage

The `AppLogger` object provides convenient logging methods:

```kotlin
import com.camilo.demo.coin.core.logging.AppLogger

// Basic logging
AppLogger.d("Debug message")
AppLogger.i("Info message")
AppLogger.w("Warning message")
AppLogger.e("Error message")
AppLogger.v("Verbose message")

// Logging with tags
AppLogger.i("Network request started", tag = "Network")

// Logging with exceptions
AppLogger.e("Request failed", exception, "Network")
```

## Log Tags

The following tags are used throughout the application:

- `Network` - HTTP requests, responses, and network errors
- `ViewModel` - UI state changes and business logic
- `UseCase` - Domain layer operations
- `HttpClient` - HTTP client configuration and setup
- `CoinsDataSource` - Data source operations
- `App` - UI lifecycle and user interactions
- `Test` - Unit test logging

## Configuration

Kermit is configured in `AppLogger` to use the platform's native logging system:
- Android: Uses Android's Log system
- iOS: Uses iOS NSLog
- Other platforms: Uses appropriate platform logging

## Testing

Run the logging tests to verify everything is working:

```bash
./gradlew composeApp:testDebugUnitTest --tests LoggerTest
```