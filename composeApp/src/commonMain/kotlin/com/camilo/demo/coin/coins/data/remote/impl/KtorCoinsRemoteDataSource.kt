package com.camilo.demo.coin.coins.data.remote.impl

import com.camilo.demo.coin.coins.data.remote.dto.CoinDetailsResponseDto
import com.camilo.demo.coin.coins.data.remote.dto.CoinPriceHistoryResponseDto
import com.camilo.demo.coin.coins.data.remote.dto.CoinsResponseDto
import io.ktor.client.HttpClient
import com.camilo.demo.coin.coins.domain.api.CoinsRemoteDataSource
import com.camilo.demo.coin.core.domain.DataError
import com.camilo.demo.coin.core.domain.Result
import com.camilo.demo.coin.core.domain.coin.Coin
import com.camilo.demo.coin.core.network.safeCall
import io.ktor.client.request.get

private const val BASE_URL = "https://api.coinranking.com/v2"

class KtorCoinsRemoteDataSource(
    private val httpClient: HttpClient
) : CoinsRemoteDataSource {

    override suspend fun getListOfCoins(): Result<CoinsResponseDto, DataError.Remote> {
        return safeCall {
            httpClient.get("$BASE_URL/coins")
        }
    }

    override suspend fun getPriceHistory(coinId: String): Result<CoinPriceHistoryResponseDto, DataError.Remote> {
        return safeCall {
            httpClient.get("$BASE_URL/coin/$coinId/history")
        }
    }

    override suspend fun getCoinById(coinId: String): Result<CoinDetailsResponseDto, DataError.Remote> {
        return safeCall {
            httpClient.get("$BASE_URL/coin/$coinId")
        }
    }
}