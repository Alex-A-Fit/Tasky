package com.alexafit.onboardingAuthPresentation.login.event

sealed interface LoginNavigationEvent {
    object Register : LoginNavigationEvent
    object Login : LoginNavigationEvent
}
