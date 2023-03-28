package com.alexafit.onboardingAuthPresentation.login.event

import com.alexafit.core.util.UiText

sealed class LoginUiEvent {
    object LoginSuccess : LoginUiEvent()
    data class ShowSnackbar(val message: UiText) : LoginUiEvent()
    object Register : LoginUiEvent()
}
