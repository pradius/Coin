package com.camilo.demo.coin.coins.presentation

import com.camilo.demo.coin.coins.domain.model.CoinModel


fun UiCoinListItem.Companion.convert(coinItem: CoinModel) : UiCoinListItem {
    return UiCoinListItem(
        id = coinItem.coin.id,
        name = coinItem.coin.name,
        iconUrl = coinItem.coin.iconUrl,
        symbol = coinItem.coin.symbol,
        formattedPrice = coinItem.price.toString(), // TODO: Format
        formattedChange = coinItem.change.toString(), // TODO: Format
        isPositive = coinItem.change >= 0
    )
}