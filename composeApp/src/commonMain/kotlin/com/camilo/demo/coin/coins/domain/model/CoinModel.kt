package com.camilo.demo.coin.coins.domain.model

import com.camilo.demo.coin.core.domain.coin.Coin

public data class CoinModel (
    val coin:Coin,
    val price: Double,
    val change: Double
)