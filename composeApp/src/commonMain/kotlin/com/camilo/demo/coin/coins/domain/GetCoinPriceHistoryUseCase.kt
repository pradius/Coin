package com.camilo.demo.coin.coins.domain

import com.camilo.demo.coin.coins.data.mapper.toPriceModel
import com.camilo.demo.coin.coins.domain.api.CoinsRemoteDataSource
import com.camilo.demo.coin.coins.domain.model.PriceModel
import com.camilo.demo.coin.core.domain.DataError
import com.camilo.demo.coin.core.domain.Result
import com.camilo.demo.coin.core.domain.map
import com.camilo.demo.coin.core.logging.AppLogger

class GetCoinPriceHistoryUseCase(
    private val client: CoinsRemoteDataSource
) {
    suspend fun execute(coinId: String): Result<List<PriceModel>, DataError.Remote> {
        AppLogger.d("Executing GetCoinPriceHistoryUseCase for coinId: $coinId", tag = "UseCase")
        return client.getPriceHistory(coinId).map { dto ->
            val priceModels = dto.data.history.map { it.toPriceModel() }
            AppLogger.d("Mapped ${priceModels.size} price history DTOs to models", tag = "UseCase")
            priceModels
        }
    }
}