package com.bk.currency.currency_detail

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.bk.currency.common.singleTopNavigator
import com.bk.currency.currency_detail.viewmodel.CurrencyDetailViewModel
import com.bk.currency.data.common.NbpTableName
import com.bk.currency.navigation.Screen
import com.bk.currency.ui.component.CurrencyItemRow
import com.bk.currency.welcome.viewmodel.MainViewModel
import timber.log.Timber

@Composable
fun CurrencyDetailScreen(
    navController: NavController,
    currencyCode: String
) {
    val detailViewModel = hiltViewModel<CurrencyDetailViewModel>()
    val uiState by detailViewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        detailViewModel.loadCurrencyDetails(NbpTableName.A /* NbpTableName.fromString(tableName)*/, currencyCode)
    }

    val rates = uiState.currencyTable?.rates
    LazyColumn(
        modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp)
    ) {
        items(rates ?: emptyList()) { item ->
            CurrencyItemRow(
                selected = false,
                currencyItem = item,
                onclick = {}
            )
        }
    }
}