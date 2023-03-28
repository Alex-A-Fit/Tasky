package com.alexafit.onboardingAuthPresentation.register.event

sealed class RegisterEvent {
    data class OnEmailAddressEnter(val emailAddress: String) : RegisterEvent()
    data class OnEmailFocusChange(val isFocused: Boolean) : RegisterEvent()
    data class OnNameEnter(val name: String) : RegisterEvent()
    data class OnNameFocusChange(val isFocused: Boolean) : RegisterEvent()
    data class OnPasswordFocusChange(val isFocused: Boolean) : RegisterEvent()
    data class OnPasswordIconClicked(val isClicked: Boolean) : RegisterEvent()
    data class OnPasswordEnter(val password: String) : RegisterEvent()
    object Register : RegisterEvent()
    object BackToLogin : RegisterEvent()
}
