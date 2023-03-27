package com.alexafit.onboardingAuthPresentation.login.event

sealed class LoginEvent {
    data class OnEmailAddressEnter(val emailAddress: String) : LoginEvent()
    data class OnEmailFocusChange(val isFocused: Boolean) : LoginEvent()
    data class OnPasswordFocusChange(val isFocused: Boolean) : LoginEvent()
    data class OnPasswordIconClicked(val isClicked: Boolean) : LoginEvent()
    data class OnPasswordEnter(val password: String) : LoginEvent()
    object Register : LoginEvent()
    object Login : LoginEvent()
}
