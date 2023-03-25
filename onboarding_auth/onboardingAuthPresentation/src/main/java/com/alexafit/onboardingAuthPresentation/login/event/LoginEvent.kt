package com.alexafit.onboardingAuthPresentation.login.event

sealed interface LoginEvent {
    object Register : LoginEvent
    object Login : LoginEvent
}
