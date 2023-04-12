package com.alexafit.onboardingAuthPresentation.event.uievent

import com.alexafit.core.util.UiText

sealed class LoginUiEvent {
    object Success : LoginUiEvent()
    data class ShowSnackbar(val message: UiText) : LoginUiEvent()
    object NavigateToRegisterScreen : LoginUiEvent()
}
