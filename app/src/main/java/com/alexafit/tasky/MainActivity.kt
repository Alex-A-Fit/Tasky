package com.alexafit.tasky

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.alexafit.agendapresentation.overview.Overview
import com.alexafit.coreui.components.loading.CircularLoadingDialog
import com.alexafit.onboardingAuthPresentation.event.navigation.NavigationEvent
import com.alexafit.onboardingAuthPresentation.login.LoginScreen
import com.alexafit.onboardingAuthPresentation.register.RegisterScreen
import com.alexafit.tasky.navigation.Route
import com.alexafit.tasky.ui.theme.TaskyTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val mainViewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val splashScreen = installSplashScreen()
        var uiState: MainActivityUiState by mutableStateOf(MainActivityUiState.Loading)

        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                mainViewModel.uiState.collect {
                    uiState = it
                }
            }
        }
        splashScreen.setKeepOnScreenCondition {
            when (uiState) {
                MainActivityUiState.Loading -> true
                MainActivityUiState.Success -> false
            }
        }
        setContent {
            TaskyTheme {
                val modifier = Modifier
                Box(
                    modifier = modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {}
                val navController = rememberNavController()
                val scaffoldState = rememberScaffoldState()
                Scaffold(
                    modifier = modifier.fillMaxSize(),
                    scaffoldState = scaffoldState
                ) { padding ->
                    NavHost(
                        navController = navController,
                        startDestination = when (mainViewModel.activeSession.value) {
                            true -> Route.AGENDA_OVERVIEW
                            false -> Route.LOGIN
                            else -> Route.LOADING_SCREEN
                        },
                        modifier = modifier.padding(padding)
                    ) {
                        composable(Route.LOADING_SCREEN) {
                            Box(
                                modifier = modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                CircularLoadingDialog(modifier = modifier)
                            }
                        }
                        composable(Route.LOGIN) {
                            LoginScreen(
                                scaffoldState = scaffoldState,
                                onEvent = { event ->
                                    when (event) {
                                        NavigationEvent.NavigateToAgenda -> navController.navigate(
                                            Route.AGENDA_OVERVIEW
                                        )
                                        NavigationEvent.NavigateToRegister -> navController.navigate(
                                            Route.REGISTER
                                        )
                                        NavigationEvent.NavigateToLogin -> Unit
                                    }
                                }

                            )
                        }
                        composable(Route.REGISTER) {
                            RegisterScreen(
                                scaffoldState = scaffoldState,
                                onEvent = { event ->
                                    when (event) {
                                        NavigationEvent.NavigateToAgenda -> navController.navigate(
                                            Route.AGENDA_OVERVIEW
                                        )
                                        NavigationEvent.NavigateToLogin -> navController.navigate(
                                            Route.LOGIN
                                        )
                                        NavigationEvent.NavigateToRegister -> Unit
                                    }
                                }
                            )
                        }
                        composable(Route.AGENDA_OVERVIEW) {
                            Overview()
                        }
                    }
                }
            }
        }
    }
}
