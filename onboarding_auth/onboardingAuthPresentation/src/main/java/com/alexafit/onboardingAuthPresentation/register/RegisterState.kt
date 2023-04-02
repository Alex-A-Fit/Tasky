package com.alexafit.onboardingAuthPresentation.register

data class RegisterState(
    var name: String = "",
    var emailAddress: String = "",
    var password: String = "",
    var validName: Boolean = false,
    var validEmailAddress: Boolean = false,
    var validPassword: Boolean = false,
    var isEmailHintVisible: Boolean = false,
    var isNameHintVisible: Boolean = false,
    var isPasswordHintVisible: Boolean = false,
    var isPasswordVisible: Boolean = true,
    var isScreenLoading: Boolean = false
)
