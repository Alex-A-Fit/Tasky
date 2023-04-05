package com.alexafit.onboardingauthdomain.useCase

import com.alexafit.onboardingauthdomain.model.remote.RegisterUser
import com.alexafit.onboardingauthdomain.repository.OnboardingAuthRepository

class RegisterUserUseCase(
    private val repository: OnboardingAuthRepository
) {
    suspend operator fun invoke(
        user: RegisterUser
    ): Result<String?> {
        return repository.registerUser(registerUser = user)
    }
}
