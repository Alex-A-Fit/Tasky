package com.alexafit.onboardingAuthPresentation.register

import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.alexafit.onboardingAuthPresentation.login.LoginViewModel
import com.alexafit.onboardingAuthPresentation.login.event.LoginEvent

@Composable
fun RegisterScreen(
    scaffoldState: ScaffoldState,
    onEventClick: (LoginEvent) -> Unit,
    viewModel: LoginViewModel = hiltViewModel()
) {
    Text(text = "This is Register Screen")
}