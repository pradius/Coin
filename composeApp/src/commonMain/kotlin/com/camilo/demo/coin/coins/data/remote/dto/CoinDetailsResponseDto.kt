package com.camilo.demo.coin.coins.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class CoinDetailsResponseDto (
    val data: CoinResponseDto
)

@Serializable
data class CoinResponseDto(
    val coin: CoinItemDto
)