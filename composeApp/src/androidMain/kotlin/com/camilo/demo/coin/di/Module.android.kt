package com.camilo.demo.coin.di

import io.ktor.client.engine.*
import io.ktor.client.engine.android.*
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformModule = module {
    // core
    single<HttpClientEngine> { Android.create() }
}