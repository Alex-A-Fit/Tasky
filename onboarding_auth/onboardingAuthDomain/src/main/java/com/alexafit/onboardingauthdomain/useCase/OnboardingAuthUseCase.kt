package com.alexafit.onboardingauthdomain.useCase

data class OnboardingAuthUseCase(
    val validateEmail: ValidateEmail,
    val validatePassword: ValidatePassword,
    val validateName: ValidateName,
    val loginUser: LoginUser,
    val registerUser: RegisterUser
)
