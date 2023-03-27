package com.alexafit.onboardingauthdomain.useCase

data class OnboardingAuthUseCase(
    val validateEmail: ValidateEmail,
    val validatePassword: ValidatePassword
)
