package com.camilo.demo.coin.di

import com.camilo.demo.coin.coins.data.remote.impl.KtorCoinsRemoteDataSource
import com.camilo.demo.coin.coins.domain.GetCoinDetailUseCase
import com.camilo.demo.coin.coins.domain.GetCoinsListUseCase
import com.camilo.demo.coin.coins.domain.api.CoinsRemoteDataSource
import com.camilo.demo.coin.coins.presentation.CoinsListViewModel
import com.camilo.demo.coin.core.network.HttpClientFactory
import io.ktor.client.HttpClient
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.bind
import org.koin.dsl.module


fun initKoin(config: KoinAppDeclaration? = null) =
    startKoin {
        config?.invoke(this)
        modules(
            sharedModule,
            platformModule,
        )

    }

expect val platformModule: Module

val sharedModule = module {
    // core
    single<HttpClient> { HttpClientFactory.create(get()) }

    // coins
    viewModel { CoinsListViewModel(get()) }
    singleOf(::GetCoinsListUseCase)
    singleOf(::KtorCoinsRemoteDataSource).bind<CoinsRemoteDataSource>()
    singleOf(::GetCoinDetailUseCase)
}
