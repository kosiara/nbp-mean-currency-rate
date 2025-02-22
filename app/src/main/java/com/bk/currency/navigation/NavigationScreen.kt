package com.bk.currency.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import com.bk.currency.R

sealed class Screen(
    val route: String,
    @StringRes val title: Int = R.string.app_name,
    val navIcon: (@Composable () -> Unit) = {
        Icon(
            Icons.Filled.Home, contentDescription = "home"
        )
    },
    val objectName: String = "",
    val objectPath: String = ""
) {
    object StartScreen : Screen("currency_list/A")
    object CurrencyList : Screen("currency_list",
        objectName = "tableName", objectPath = "/{tableName}")
    object CurrencyDetail :
        Screen("currency_detail", objectName = "movieItem", objectPath = "/{movieItem}")
}