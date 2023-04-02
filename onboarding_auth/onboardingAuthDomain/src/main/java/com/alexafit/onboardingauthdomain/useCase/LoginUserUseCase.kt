package com.alexafit.onboardingauthdomain.useCase

import com.alexafit.onboardingauthdata.model.local.Login
import com.alexafit.onboardingauthdata.repository.OnboardingAuthRepository
import com.alexafit.onboardingauthdomain.mapper.mapToDto
import com.alexafit.onboardingauthdomain.model.remote.LoginUser

class LoginUserUseCase(
    private val repository: OnboardingAuthRepository
) {
    suspend operator fun invoke(
        user: LoginUser
    ): Result<Login?> {
        return repository.loginUser(
            loginDtoBody = user.mapToDto()
        )
    }
}
