package com.alexafit.onboardingAuthPresentation.event.user

sealed class LoginUserEvent {
    data class OnEmailAddressEnter(val emailAddress: String) : LoginUserEvent()
    data class OnEmailFocusChange(val isFocused: Boolean) : LoginUserEvent()
    data class OnPasswordFocusChange(val isFocused: Boolean) : LoginUserEvent()
    data class OnPasswordIconClicked(val isClicked: Boolean) : LoginUserEvent()
    data class OnPasswordEnter(val password: String) : LoginUserEvent()
    object NavigateToAgenda : LoginUserEvent()
    object NavigateToRegister : LoginUserEvent()
}
