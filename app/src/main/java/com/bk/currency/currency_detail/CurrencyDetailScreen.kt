package com.bk.currency.currency_detail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.bk.currency.currency_detail.viewmodel.CurrencyDetailViewModel
import com.bk.currency.data.common.NbpTableName
import com.bk.currency.android.common.designsystem.component.CurrencyDetailList

@Composable
fun CurrencyDetailScreen(
    navController: NavController,
    tableName: String,
    currencyCode: String,
) {
    val detailViewModel = hiltViewModel<CurrencyDetailViewModel>()
    val uiState by detailViewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        detailViewModel.loadCurrencyDetails(NbpTableName.fromString(tableName), currencyCode)
    }

    val rates = uiState.currencyTable?.rates
    CurrencyDetailList(
        uiState.currencyTable?.currency ?: stringResource(com.bk.currency.android.common.R.string.no_data),
        uiState.currencyTable?.code ?: stringResource(com.bk.currency.android.common.R.string.no_data),
        rates ?: emptyList(),
    )
}
