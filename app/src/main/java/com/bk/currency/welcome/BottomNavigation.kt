package com.bk.currency.welcome

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.bk.currency.android.common.common.singleTopNavigator
import com.bk.currency.android.common.navigation.Screen

@Composable
fun BottomNavigationUI(navController: NavController) {
    NavigationBar {
        val items = listOf(
            BottomTab.TableA,
            BottomTab.TableB,
        )
        items.forEachIndexed { _, item ->
            NavigationBarItem(icon = item.navIcon,
                label = { Text(text = item.name) },
                selected = false, //currentRoute(navController) == item.route,
                onClick = {
                    navController.singleTopNavigator(item.navigationPath.plus("/${item.argumentTableName}"))
                })
        }
    }
}

sealed class BottomTab(
    val name: String,
    val navigationPath: String,
    val argumentTableName: String = "A",
    val navIcon: (@Composable () -> Unit) = {
        Icon(
            Icons.Filled.Home, contentDescription = "home"
        )
    },
) {
    object TableA : BottomTab("Table A", Screen.CurrencyList.route, "A")
    object TableB : BottomTab("Table B", Screen.CurrencyList.route, "B",
        navIcon = {
            Icon(
                Icons.Filled.Star, contentDescription = "star"
            )
        }
    )
}
