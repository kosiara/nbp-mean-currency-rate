package com.bk.currency.android.common.common

import androidx.navigation.NavController

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
