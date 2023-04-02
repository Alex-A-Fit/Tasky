package com.alexafit.onboardingauthdomain.useCase

import com.alexafit.onboardingauthdata.model.remote.Login
import com.alexafit.onboardingauthdata.model.remote.LoginResponse
import com.alexafit.onboardingauthdata.repository.OnboardingAuthRepository

class LoginUser(
    private val repository: OnboardingAuthRepository
) {
    suspend operator fun invoke(
        email: String,
        passord: String
    ): Result<LoginResponse> {
        return repository.loginUser(
            Login(
                email = email,
                password = passord
            )
        )
    }
}
