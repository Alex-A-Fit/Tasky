package com.alexafit.onboardingAuthPresentation.register.event

import com.alexafit.core.util.UiText

sealed class RegisterUiEvent {
    object RegisterSuccess : RegisterUiEvent()
    data class ShowSnackbar(val message: UiText) : RegisterUiEvent()
    object BackToLogin : RegisterUiEvent()
}
