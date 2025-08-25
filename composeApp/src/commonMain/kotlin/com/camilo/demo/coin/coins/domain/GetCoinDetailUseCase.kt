package com.camilo.demo.coin.coins.domain

import com.camilo.demo.coin.coins.domain.api.CoinsRemoteDataSource
import com.camilo.demo.coin.coins.domain.model.CoinModel
import com.camilo.demo.coin.core.domain.DataError
import com.camilo.demo.coin.core.domain.Result
import com.camilo.demo.coin.core.domain.map
import com.camilo.demo.coin.core.logging.AppLogger
import com.camilo.demo.coin.coins.data.mapper.toCoinModel

class GetCoinDetailUseCase(
    private val client: CoinsRemoteDataSource
) {
    suspend fun execute(coinId: String): Result<CoinModel, DataError.Remote> {
        AppLogger.d("Executing GetCoinDetailUseCase for coinId: $coinId", tag = "UseCase")
        return client.getCoinById(coinId).map { dto ->
            val coinModel = dto.data.coin.toCoinModel()
            AppLogger.d("Mapped coin detail DTO to model for: ${coinModel.name}", tag = "UseCase")
            coinModel
        }
    }
}