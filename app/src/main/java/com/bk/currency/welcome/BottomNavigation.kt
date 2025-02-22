package com.bk.currency.welcome

import androidx.compose.foundation.pager.PagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
//import com.piashcse.hilt_mvvm_compose_movie.navigation.Screen
//import com.piashcse.hilt_mvvm_compose_movie.navigation.currentRoute
//import com.piashcse.hilt_mvvm_compose_movie.utils.singleTopNavigator

@Composable
fun BottomNavigationUI(navController: NavController) {
    NavigationBar {
//        val items = if (pagerState.currentPage == 0) {
//            listOf(
//                Screen.NowPlayingNav,
//                Screen.PopularNav,
//                Screen.TopRatedNav,
//                Screen.UpcomingNav,
//            )
//        } else {
//            listOf(
//                Screen.AiringTodayTvSeriesNav,
//                Screen.OnTheAirTvSeriesNav,
//                Screen.PopularTvSeriesNav,
//                Screen.TopRatedTvSeriesNav,
//            )
//        }
//        items.forEachIndexed { index, item ->
//            NavigationBarItem(icon = item.navIcon,
//                label = { Text(text = stringResource(id = item.title)) },
//                selected = currentRoute(navController) == item.route,
//                onClick = {
//                    navController.singleTopNavigator(item.route)
//                })
//        }

        NavigationBarItem(icon = { Icons.Filled.Home },
                label = { Text(text = "TABLE A") },
                selected = false,
                onClick = {
                    //navController.singleTopNavigator(item.route)
                })
    }
}
