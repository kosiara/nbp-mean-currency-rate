package com.bk.currency.navigation

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
import com.bk.currency.data.common.NbpTableName

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
    val argumentTableName: String = NbpTableName.A.name,
    val navIcon: (@Composable () -> Unit) = {
        Icon(
            Icons.Filled.Home, contentDescription = "home"
        )
    },
) {
    object TableA : BottomTab("Table ${NbpTableName.A.name}", Screen.CurrencyList.route, NbpTableName.A.name)
    object TableB : BottomTab("Table ${NbpTableName.B.name}", Screen.CurrencyList.route, NbpTableName.B.name,
        navIcon = {
            Icon(
                Icons.Filled.Star, contentDescription = "star"
            )
        }
    )
}
