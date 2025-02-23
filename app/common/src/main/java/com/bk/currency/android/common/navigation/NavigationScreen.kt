package com.bk.currency.android.common.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import com.bk.currency.android.common.R

sealed class Screen(
    val route: String,
    @StringRes val title: Int = R.string.app_name,
    val navIcon: (@Composable () -> Unit) = {
        Icon(
            Icons.Filled.Home, contentDescription = "home"
        )
    },
    val objectPath: String = "",
    val objectName: String = "",
    val secondaryObjectName: String = "",
) {
    object StartScreen : Screen("currency_list/A")
    object CurrencyList : Screen("currency_list",
        objectName = "tableName", objectPath = "/{tableName}")
    object CurrencyDetail :
        Screen("currency_detail", objectPath = "/{tableName}/{currencyCode}",
            objectName = "tableName", secondaryObjectName = "currencyCode",
        )
}