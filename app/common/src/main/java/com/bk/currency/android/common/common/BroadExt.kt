package com.bk.currency.android.common.common

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.navigation.NavController
import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun NavController.singleTopNavigator(route: String) {
    this.navigate(route) {
        graph.startDestinationRoute?.let { route ->
            popUpTo(route) {
                inclusive = true
            }
        }
        launchSingleTop = true
        restoreState = true
    }
}

fun NavController.navigateWithHistory(route: String) {
    this.navigate(route) {
        restoreState = true
    }
}
