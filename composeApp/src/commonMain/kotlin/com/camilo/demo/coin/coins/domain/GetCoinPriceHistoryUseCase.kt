package com.camilo.demo.coin.coins.domain

import com.camilo.demo.coin.coins.data.mapper.toPriceModel
import com.camilo.demo.coin.coins.domain.api.CoinsRemoteDataSource
import com.camilo.demo.coin.coins.domain.model.CoinModel
import com.camilo.demo.coin.core.domain.DataError
import com.camilo.demo.coin.core.domain.Result
import com.camilo.demo.coin.core.domain.map
import com.camilo.demo.coin.coins.domain.model.PriceModel

class GetCoinPriceHistoryUseCase(
    private val client: CoinsRemoteDataSource
) {
    suspend fun execute(coinId: String): Result<List<PriceModel>, DataError.Remote> {
        return client.getPriceHistory(coinId).map { dto ->
            dto.data.history.map { it.toPriceModel() }
        }
    }
}