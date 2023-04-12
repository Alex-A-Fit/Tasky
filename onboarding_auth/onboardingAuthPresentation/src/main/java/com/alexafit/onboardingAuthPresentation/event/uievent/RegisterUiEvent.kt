package com.alexafit.onboardingAuthPresentation.event.uievent

import com.alexafit.core.util.UiText

sealed class RegisterUiEvent {
    object Success : RegisterUiEvent()
    data class ShowSnackbar(val message: UiText) : RegisterUiEvent()
    object NavigateToLoginScreen : RegisterUiEvent()
}
