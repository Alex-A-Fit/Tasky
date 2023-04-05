package com.alexafit.onboardingauthdomain.useCase

import com.alexafit.onboardingauthdomain.model.remote.LoginUser
import com.alexafit.onboardingauthdomain.repository.OnboardingAuthRepository

class LoginUserUseCase(
    private val repository: OnboardingAuthRepository
) {
    suspend operator fun invoke(
        user: LoginUser
    ): Result<String?> {
        return repository.loginUser(loginUser = user)
    }
}
