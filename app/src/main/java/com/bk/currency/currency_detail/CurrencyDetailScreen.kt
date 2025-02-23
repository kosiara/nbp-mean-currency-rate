package com.bk.currency.currency_detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.bk.currency.currency_detail.viewmodel.CurrencyDetailViewModel
import com.bk.currency.data.common.NbpTableName
import com.bk.currency.data.mock.DataMock
import com.bk.currency.data.model.CurrencyItem
import com.bk.currency.ui.component.HighlightingItemRow

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
    CurrencyDetailList(
        "Dolar amerykański",
        "USD",
        rates ?: emptyList(),
    )
}

@Composable
fun CurrencyDetailList(
    name: String,
    code: String,
    rates: List<CurrencyItem>
) {
    Column {
        Text(
            text = name,
            modifier = Modifier.padding(start = 30.dp, top = 30.dp, bottom = 8.dp),
            style = MaterialTheme.typography.bodyLarge
        )
        Text(
            text = code,
            modifier = Modifier.padding(start = 30.dp, bottom = 12.dp),
            style = MaterialTheme.typography.bodyLarge
        )
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp)
        ) {
            items(rates) { item ->
                HighlightingItemRow(
                    currencyItem = item,
                    onclick = {}
                )
            }
        }
    }
}

@Preview(name = "CurrencyDetailScreen", showBackground = true)
@Composable
fun PreviewCurrencyDetailList() {
    CurrencyDetailList(
        "Dolar amerykański",
        "USD",
        DataMock.singleCurrencyItems,
    )
}