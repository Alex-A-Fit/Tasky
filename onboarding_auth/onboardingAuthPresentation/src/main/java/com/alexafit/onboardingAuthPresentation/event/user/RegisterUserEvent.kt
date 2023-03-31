package com.alexafit.onboardingAuthPresentation.event.user

sealed class RegisterUserEvent {
    data class OnEmailAddressEnter(val emailAddress: String) : RegisterUserEvent()
    data class OnEmailFocusChange(val isFocused: Boolean) : RegisterUserEvent()
    data class OnPasswordFocusChange(val isFocused: Boolean) : RegisterUserEvent()
    data class OnPasswordIconClicked(val isClicked: Boolean) : RegisterUserEvent()
    data class OnPasswordEnter(val password: String) : RegisterUserEvent()
    data class OnNameEnter(val name: String) : RegisterUserEvent()
    data class OnNameFocusChange(val isFocused: Boolean) : RegisterUserEvent()
    object NavigateToAgenda : RegisterUserEvent()
    object NavigateToLogin : RegisterUserEvent()
}
