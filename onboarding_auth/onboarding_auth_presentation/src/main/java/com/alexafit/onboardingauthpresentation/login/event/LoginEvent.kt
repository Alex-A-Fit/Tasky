package com.alexafit.onboardingauthpresentation.login.event

sealed interface LoginEvent {
    object Register : LoginEvent
    object Login : LoginEvent
}
