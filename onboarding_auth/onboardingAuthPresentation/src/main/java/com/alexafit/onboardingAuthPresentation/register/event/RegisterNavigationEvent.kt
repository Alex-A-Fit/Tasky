package com.alexafit.onboardingAuthPresentation.register.event

sealed interface RegisterNavigationEvent {
    object Register : RegisterNavigationEvent
    object BackToLogin : RegisterNavigationEvent
}
