package com.alexafit.onboardingAuthPresentation.login

data class LoginState(
    var emailAddress: String = "",
    var password: String = "",
    var validEmailAddress: Boolean = false,
    var validPassword: Boolean = false,
    var isEmailHintVisible: Boolean = false,
    var isPasswordHintVisible: Boolean = false,
    var isPasswordVisible: Boolean = true,
    var isScreenLoading: Boolean = false
)
