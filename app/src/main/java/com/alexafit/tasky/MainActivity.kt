package com.alexafit.tasky

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.alexafit.onboardingAuthPresentation.login.LoginScreen
import com.alexafit.onboardingAuthPresentation.login.event.LoginNavigationEvent
import com.alexafit.onboardingAuthPresentation.register.RegisterScreen
import com.alexafit.tasky.navigation.Route
import com.alexafit.tasky.ui.theme.TaskyTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // need to check to see if user has active session
        val activeSession = false
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
                        startDestination = if (activeSession) Route.AGENDA_OVERVIEW else Route.LOGIN,
                        modifier = Modifier.padding(padding)
                    ) {
                        composable(Route.LOGIN) {
                            LoginScreen(
                                scaffoldState = scaffoldState,
                                onEvent = { event ->
                                    when (event) {
                                        LoginNavigationEvent.Login -> navController.navigate(Route.AGENDA_OVERVIEW)
                                        LoginNavigationEvent.Register -> navController.navigate(Route.REGISTER)
                                    }
                                }

                            )
                        }
                        composable(Route.REGISTER) {
                            RegisterScreen(
                                scaffoldState = scaffoldState,
                                onEventClick = { event ->
                                    when (event) {
                                        LoginNavigationEvent.Login -> navController.navigate(Route.AGENDA_OVERVIEW)
                                        LoginNavigationEvent.Register -> navController.navigate(Route.REGISTER)
                                    }
                                }

                            )
                        }
                    }
                }
            }
        }
    }
}
