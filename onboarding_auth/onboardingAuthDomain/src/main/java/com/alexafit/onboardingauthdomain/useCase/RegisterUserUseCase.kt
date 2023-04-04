package com.alexafit.onboardingauthdomain.useCase

import com.alexafit.onboardingauthdata.repository.OnboardingAuthRepository
import com.alexafit.onboardingauthdomain.mapper.mapToDto
import com.alexafit.onboardingauthdomain.model.remote.RegisterUser

class RegisterUserUseCase(
    private val repository: OnboardingAuthRepository
) {
    suspend operator fun invoke(
        user: RegisterUser
    ): Result<String?> {
        return repository.registerUser(
            registerDtoBody = user.mapToDto()
        )
    }
}
