package com.camilo.demo.coin.di

actual val platformModule = module {
    // core
    single<HttpClientEngine> { Darwin.create() }
}