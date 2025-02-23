package com.bk.currency.features.currency_list

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
import com.bk.currency.android.common.common.navigateWithHistory
import com.bk.currency.data.common.NbpTableName
import com.bk.currency.android.common.navigation.Screen
import com.bk.currency.android.common.designsystem.component.CurrencyItemRow
import com.bk.currency.features.currency_list.viewmodel.MainViewModel
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
                currencyItem = item,
                onclick = {
                    navController.navigateWithHistory(
                        Screen.CurrencyDetail.route
                        .plus("/${uiState.currencyTable?.tableName}")
                        .plus("/${item.code}"))
                }
            )
        }
    }
}