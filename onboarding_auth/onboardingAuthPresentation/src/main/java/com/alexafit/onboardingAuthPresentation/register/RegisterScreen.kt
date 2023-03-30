package com.alexafit.onboardingAuthPresentation.register

import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.alexafit.onboardingAuthPresentation.event.navigation.NavigationEvent

@Composable
fun RegisterScreen(
    scaffoldState: ScaffoldState,
    onEventClick: (NavigationEvent) -> Unit,
    viewModel: RegisterViewModel = hiltViewModel()
) {
    Text(text = "This is Register Screen")
}
