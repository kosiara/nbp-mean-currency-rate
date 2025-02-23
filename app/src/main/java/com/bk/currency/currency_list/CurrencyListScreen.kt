package com.bk.currency.currency_list

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
import com.bk.currency.data.common.NbpTableName
import com.bk.currency.ui.component.CurrencyItemRow
import com.bk.currency.welcome.viewmodel.MainViewModel
import timber.log.Timber

@Composable
fun CurrencyListScreen(
    navController: NavController,
    tableName: String?
) {
    val mainViewModel = hiltViewModel<MainViewModel>()
    val uiState by mainViewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        if (tableName == null) {
          Timber.e("tableName is null")
        } else {
            mainViewModel.loadCurrencies(NbpTableName.fromString(tableName))
        }
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