package com.alexafit.onboardingauthdomain.useCase

import com.alexafit.onboardingauthdata.repository.OnboardingAuthRepository
import com.alexafit.onboardingauthdomain.mapper.mapToDto
import com.alexafit.onboardingauthdomain.model.remote.LoginUser

class LoginUserUseCase(
    private val repository: OnboardingAuthRepository
) {
    suspend operator fun invoke(
        user: LoginUser
    ): Result<String?> {
        return repository.loginUser(
            loginDtoBody = user.mapToDto()
        )
    }
}
