package com.bk.currency.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navArgument
import com.bk.currency.R
import com.bk.currency.android.common.navigation.Screen
import com.bk.currency.features.currency_list.CurrencyListScreen

@Composable
fun Navigation(
    navController: NavHostController,
) {
    NavHost(navController, startDestination =  Screen.StartScreen.route) {
        composable(
            Screen.CurrencyList.route.plus(Screen.CurrencyList.objectPath),
            arguments = listOf(navArgument(Screen.CurrencyList.objectName) {
                type = NavType.StringType
            })
        ) {
            label = stringResource(R.string.currency_list_screen)
            val tableName = it.arguments?.getString(Screen.CurrencyList.objectName)
            tableName?.let {
                CurrencyListScreen(
                    navController = navController,
                    tableName = tableName,
                )
            }
        }

        composable(
            Screen.CurrencyDetail.route.plus(Screen.CurrencyDetail.objectPath),
            arguments = listOf(navArgument(Screen.CurrencyDetail.objectName) {
                type = NavType.StringType
            }, navArgument(Screen.CurrencyDetail.secondaryObjectName) {
                type = NavType.StringType
            })
        ) {
            label = stringResource(R.string.currency_details_screen)
            val tableName = it.arguments?.getString(Screen.CurrencyDetail.objectName)
            val currencyCode = it.arguments?.getString(Screen.CurrencyDetail.secondaryObjectName)
            tableName?.let {
                currencyCode?.let {
                    com.bk.currency.features.currency_detail.CurrencyDetailScreen(
                        navController = navController, tableName, currencyCode
                    )
                }
            }
        }
    }
}

@Composable
fun navigationTitle(navController: NavController): String {
    val currentRoute = currentRoute(navController)
    return if (currentRoute?.contains(Screen.CurrencyList.route) == true) {
        stringResource(id = R.string.currency_list_screen)
    } else if (currentRoute?.contains(Screen.CurrencyDetail.route) == true) {
        stringResource(id = R.string.currency_details_screen)
    } else {
        stringResource(com.bk.currency.android.common.R.string.app_name)
    }
}

@Composable
fun currentRoute(navController: NavController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route?.substringBeforeLast("/")
}
