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
import com.bk.currency.currency_list.CurrencyListScreen
import com.bk.currency.data.model.CurrencyTable

@Composable
fun Navigation(
    navController: NavHostController, currencyTable: CurrencyTable? = null,
) {
    NavHost(navController, startDestination =  Screen.CurrencyList.route) {
        composable(Screen.CurrencyList.route) {
            CurrencyListScreen(
                navController = navController,
                currencyTable,
            )
        }

//        composable(
//            Screen.CurrencyDetail.route.plus(Screen.MovieDetail.objectPath),
//            arguments = listOf(navArgument(Screen.MovieDetail.objectName) {
//                type = NavType.IntType
//            })
//        ) {
//            label = stringResource(R.string.movie_detail)
//            val movieId = it.arguments?.getInt(Screen.MovieDetail.objectName)
//            movieId?.let {
//                MovieDetail(
//                    navController = navController, movieId
//                )
//            }
//        }

    }
}

@Composable
fun navigationTitle(navController: NavController): String {
    return when (currentRoute(navController)) {
        Screen.CurrencyList.route -> stringResource(id = R.string.currency_list_screen)
        Screen.CurrencyDetail.route -> stringResource(id = R.string.currency_details_screen)
        else -> {
            stringResource(R.string.app_name)
        }
    }
}

@Composable
fun currentRoute(navController: NavController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route?.substringBeforeLast("/")
}
