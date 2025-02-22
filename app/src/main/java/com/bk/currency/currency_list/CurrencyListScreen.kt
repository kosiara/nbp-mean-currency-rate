package com.bk.currency.currency_list

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.bk.currency.data.model.CurrencyTable
import com.bk.currency.ui.component.CurrencyItemRow

@Composable
fun CurrencyListScreen(
    navController: NavController,
    currencyTable: CurrencyTable?
) {
    val rates = currencyTable?.rates
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