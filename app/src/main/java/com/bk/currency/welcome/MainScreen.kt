package com.bk.currency.welcome

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
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
import com.bk.currency.android.common.navigation.Screen
import com.bk.currency.navigation.currentRoute
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
                    color = Color.Black
                )
            },
                navigationIcon = {
                val activeScreen = currentRoute(navController)
                if (activeScreen?.contains(Screen.CurrencyDetail.route) == true) {
                    IconButton(
                        onClick = { navController.popBackStack() }
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "back arrow",
                            tint = Color.Black
                        )
                    }
                }
            },
            )
        },
        bottomBar = {
            BottomNavigationUI(navController)
        },
        snackbarHost = {
            if (uiState.error != null) {
                Snackbar(
                    action = {}, modifier = Modifier.padding(8.dp)
                ) {
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