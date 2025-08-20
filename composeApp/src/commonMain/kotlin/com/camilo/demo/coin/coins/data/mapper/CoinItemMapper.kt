package com.camilo.demo.coin.coins.data.mapper

import com.camilo.demo.coin.coins.data.remote.dto.CoinItemDto
import com.camilo.demo.coin.coins.data.remote.dto.CoinPriceDto
import com.camilo.demo.coin.coins.domain.model.CoinModel
import com.camilo.demo.coin.coins.domain.model.PriceModel
import com.camilo.demo.coin.core.domain.coin.Coin

fun CoinItemDto.toCoinModel() = CoinModel(
    coin = Coin(id = uuid, symbol = symbol, name = name, iconUrl = iconUrl),
    price = price,
    change = change
)

fun CoinPriceDto.toPriceModel() = PriceModel(
    price = price,
    timestamp = timestamp,
)
