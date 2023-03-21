package com.alexafit.onboarding_auth_presentation.login.event

sealed interface LoginEvent {
    object Register : LoginEvent
    object Login : LoginEvent
}
