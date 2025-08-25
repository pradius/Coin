package com.camilo.demo.coin.coins.domain

import com.camilo.demo.coin.coins.domain.api.CoinsRemoteDataSource
import com.camilo.demo.coin.coins.domain.model.CoinModel
import com.camilo.demo.coin.core.domain.DataError
import com.camilo.demo.coin.core.domain.Result
import com.camilo.demo.coin.core.domain.map
import com.camilo.demo.coin.core.logging.AppLogger
import com.camilo.demo.coin.coins.data.mapper.toCoinModel

class GetCoinsListUseCase(
    private val client: CoinsRemoteDataSource
) {
    suspend fun execute(): Result<List<CoinModel>, DataError.Remote> {
        AppLogger.d("Executing GetCoinsListUseCase", tag = "UseCase")
        return client.getListOfCoins().map { dto ->
            val coinModels = dto.data.coins.map{ it.toCoinModel()}
            AppLogger.d("Mapped ${coinModels.size} coin DTOs to models", tag = "UseCase")
            coinModels
        }
    }
}