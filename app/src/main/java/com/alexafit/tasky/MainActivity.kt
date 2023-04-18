package com.alexafit.tasky

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.alexafit.agendapresentation.overview.Overview
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
                val navController = rememberNavController()
                val scaffoldState = rememberScaffoldState()
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    scaffoldState = scaffoldState
                ) { padding ->
                    NavHost(
                        navController = navController,
                        startDestination = Route.AGENDA_OVERVIEW,
//                        if (mainViewModel.activeSession.value == true) Route.AGENDA_OVERVIEW else Route.LOGIN,
                        modifier = Modifier.padding(padding)
                    ) {
                        composable(Route.LOGIN) {
                            LoginScreen(
                                scaffoldState = scaffoldState,
                                onEvent = ::authenticationNavigation

                            )
                        }
                        composable(Route.REGISTER) {
                            RegisterScreen(
                                scaffoldState = scaffoldState,
                                onEvent = ::authenticationNavigation
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
    private fun authenticationNavigation(navigationEvent: NavigationEvent) {
        when (navigationEvent) {
            NavigationEvent.NavigateToAgenda -> {
                Route.AGENDA_OVERVIEW
            }
            NavigationEvent.NavigateToLogin -> {
                Route.LOGIN
            }
            NavigationEvent.NavigateToRegister -> {
                Route.REGISTER
            }
        }
    }
}
