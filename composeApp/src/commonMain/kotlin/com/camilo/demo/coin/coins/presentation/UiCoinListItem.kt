package com.camilo.demo.coin.coins.presentation

import com.camilo.demo.coin.coins.domain.model.CoinModel

data class UiCoinListItem (
    val id: String,
    val name: String,
    val symbol: String,
    val iconUrl: String,
    val formattedPrice: String,
    val formattedChange: String,
    val isPositive: Boolean,


) {
    companion object
}

