package com.alexafit.onboardingauthdomain.useCase

data class OnboardingAuthUseCase(
    val validateEmail: ValidateEmail,
    val validatePassword: ValidatePassword,
    val validateName: ValidateName,
    val loginUserUseCase: LoginUserUseCase,
    val registerUserUseCase: RegisterUserUseCase,
    val setAuthorizationTokenUseCase: SetAuthorizationTokenUseCase
)
