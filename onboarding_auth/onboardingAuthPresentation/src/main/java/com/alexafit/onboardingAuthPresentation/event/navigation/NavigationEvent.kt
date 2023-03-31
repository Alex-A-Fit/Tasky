package com.alexafit.onboardingAuthPresentation.event.navigation

sealed interface NavigationEvent {
    object NavigateToAgenda : NavigationEvent
    object NavigateToLogin : NavigationEvent
    object NavigateToRegister : NavigationEvent
}
