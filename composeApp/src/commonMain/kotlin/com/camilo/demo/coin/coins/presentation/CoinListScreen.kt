package com.camilo.demo.coin.coins.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage

@Composable
fun CoinsListScreen(
    onCoinClicked: (String) -> Unit,
){
    val coinsListViewModel = viewModel(CoinsListViewModel::class)
    val state by coinsListViewModel.state.collectAsStateWithLifecycle()

    CoinsListContent(
        state = state,
        onCoinClicked = onCoinClicked,
    )
}

@Composable
fun CoinsListContent(
    state: CoinState,
    onCoinClicked: (String) -> Unit
){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ){
        CoinsList(
            coins = state.coins,
            onCoinClicked = onCoinClicked
        )
    }
}

@Composable
fun CoinsList(
    coins: List<UiCoinListItem>,
    onCoinClicked: (String) -> Unit,
) {
   Box(
       contentAlignment = Alignment.Center,
       modifier = Modifier.background(MaterialTheme.colorScheme.background)
   )
   {
       LazyColumn(
           verticalArrangement = Arrangement.spacedBy(8.dp),
           modifier = Modifier.fillMaxSize()
       ){
           item {
               Text(
                   text = "🔥 Top Coins:",
                   color = MaterialTheme.colorScheme.onBackground,
                   fontSize = MaterialTheme.typography.titleLarge.fontSize,
                   modifier = Modifier.padding(16.dp)
               )
           }
           items(coins) { coin ->
               CoinListItem(
                   coin = coin,
                   onCoinClicked = onCoinClicked,
               )
           }
       }
   }
}

@Composable
fun CoinListItem(
    coin: UiCoinListItem,
    onCoinClicked: (String) -> Unit
)
{
    Row(
       verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxSize()
            .clickable { onCoinClicked(coin.id) }
            .padding(horizontal = 16.dp)
    ){
        AsyncImage(
            model = coin.iconUrl,
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .padding(4.dp)
                .clip(CircleShape)
                .size(40.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = coin.name,
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = MaterialTheme.typography.titleMedium.fontSize,
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = coin.symbol,
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = MaterialTheme.typography.bodyMedium.fontSize,
            )
        }
        Spacer(modifier = Modifier.width(16.dp))
        Column(
            horizontalAlignment = Alignment.End,
        ) {
            Text(
                text = coin.formattedPrice,
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = MaterialTheme.typography.titleMedium.fontSize,
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = coin.formattedChange,
                color = if (coin.isPositive) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error,
                fontSize = MaterialTheme.typography.titleMedium.fontSize,
            )
        }
    }
}