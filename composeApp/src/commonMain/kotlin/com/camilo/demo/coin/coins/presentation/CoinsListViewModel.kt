package com.camilo.demo.coin.coins.presentation

import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.camilo.demo.coin.coins.domain.GetCoinsListUseCase
import com.camilo.demo.coin.coins.domain.model.CoinModel
import com.camilo.demo.coin.core.logging.AppLogger
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import com.camilo.demo.coin.core.domain.Result
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class CoinsListViewModel(
    private val getCoinsListUseCase: GetCoinsListUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(CoinState())
    val state = _state
        .onStart {
            AppLogger.i("CoinsListViewModel started, fetching coins", tag = "ViewModel")
            getAllCoins()
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = CoinState()
        )

    private suspend fun getAllCoins() {
        AppLogger.d("Getting all coins", tag = "ViewModel")
        when(val coinsResponse = getCoinsListUseCase.execute()) {
            is Result.Success -> {
                AppLogger.i("Successfully fetched ${coinsResponse.data.size} coins", tag = "ViewModel")
                _state.update {
                    CoinState(
                        coins = coinsResponse.data.map { coinItem ->
                            UiCoinListItem.convert(coinItem)
                        }
                    )
                }
            }
            is Result.Error -> {
                AppLogger.e("Failed to fetch coins: ${coinsResponse.error}", tag = "ViewModel")
                _state.update {
                    it.copy(
                        coins = emptyList(),
                        error = null
                    )
                }
            }
        }
    }
}
