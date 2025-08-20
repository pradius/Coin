package com.camilo.demo.coin.coins.domain.api

import com.camilo.demo.coin.coins.data.remote.dto.CoinDetailsResponseDto
import com.camilo.demo.coin.coins.data.remote.dto.CoinPriceHistoryResponseDto
import com.camilo.demo.coin.coins.data.remote.dto.CoinsResponseDto
import com.camilo.demo.coin.core.domain.DataError
import com.camilo.demo.coin.core.domain.Result

interface CoinsRemoteDataSource {
    suspend fun getListOfCoins(): Result<CoinsResponseDto, DataError.Remote>
    suspend fun getPriceHistory(coinId: String): Result<CoinPriceHistoryResponseDto, DataError.Remote>
    suspend fun getCoinById(coinId: String): Result<CoinDetailsResponseDto, DataError.Remote>
}