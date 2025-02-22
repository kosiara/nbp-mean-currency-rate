package com.bk.currency.welcome

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.bk.currency.R
import com.bk.currency.navigation.Navigation
import com.bk.currency.navigation.navigationTitle
import com.bk.currency.ui.component.CircularIndeterminateProgressBar
import com.bk.currency.welcome.viewmodel.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val mainViewModel = hiltViewModel<MainViewModel>()
    val navController = rememberNavController()
    val uiState by mainViewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                titleContentColor = MaterialTheme.colorScheme.primary,
            ), title = {
                Text(
                    text = navigationTitle(navController),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    color = Color.White
                )
            },
//                navigationIcon = {
//                when (currentRoute(navController)) {
//                    Screen.MovieDetail.route, Screen.ArtistDetail.route, Screen.TvSeriesDetail.route, Screen.FavoriteMovie.route, Screen.FavoriteTvSeries.route -> {
//                        val activeScreen = currentRoute(navController)
//                        IconButton(onClick = {
//                            if (isFavoriteActive.value && (activeScreen == Screen.FavoriteMovie.route || activeScreen == Screen.FavoriteTvSeries.route)) {
//                                val activeMovieTab =
//                                    if (pagerState.currentPage == ACTIVE_MOVIE_TAB) Screen.NowPlaying.route else Screen.AiringTodayTvSeries.route
//                                navController.navigate(activeMovieTab) {
//                                    popUpTo(navController.graph.startDestinationId) {
//                                        inclusive = true
//                                    }
//                                }
//                                isFavoriteActive.value = false
//                            } else {
//                                navController.popBackStack()
//                            }
//                        }) {
//                            Icon(
//                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
//                                contentDescription = "back arrow",
//                                tint = Color.White
//                            )
//                        }
//                    }
//                }
//            },
//                scrollBehavior = scrollBehavior, actions = {
//                IconButton(onClick = {
//                    if (!isFavoriteActive.value) navController.navigate(Screen.FavoriteMovie.route)
//                    isFavoriteActive.value = true
//                }) {
//                    if (currentRoute(navController) !in listOf(
//                            Screen.FavoriteMovie.route,
//                            Screen.FavoriteTvSeries.route,
//                            Screen.MovieDetail.route,
//                            Screen.TvSeriesDetail.route,
//                            Screen.ArtistDetail.route
//                        )
//                    ) {
//                        Icon(
//                            imageVector = Icons.Filled.Favorite,
//                            contentDescription = "favorite",
//                            tint = Color.Gray
//                        )
//                    }
//                }
//            }
            )
        },
        bottomBar = {
            BottomNavigationUI(navController)
        },
        snackbarHost = {
            if (uiState.error != null) {
                Snackbar(
                    action = {}, modifier = Modifier.padding(8.dp)
                )
                {
                    Text(text = stringResource(R.string.no_internet))
                }
            }
        }
    ) { padding ->
        Box(Modifier.padding(padding)) {
            MainView(
                navController = navController,
            )
            CircularIndeterminateProgressBar(isDisplayed = uiState.isLoading, 0.1f)
        }
    }
}

@Composable
fun MainView(
    navController: NavHostController,
) {
    Column {
        Navigation(navController)
    }
}