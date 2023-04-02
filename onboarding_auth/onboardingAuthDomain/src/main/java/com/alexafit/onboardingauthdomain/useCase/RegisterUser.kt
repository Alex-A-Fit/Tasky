package com.alexafit.onboardingauthdomain.useCase

import com.alexafit.onboardingauthdata.model.remote.Register
import com.alexafit.onboardingauthdata.model.remote.RegisterResponse
import com.alexafit.onboardingauthdata.repository.OnboardingAuthRepository

class RegisterUser(
    private val repository: OnboardingAuthRepository
) {
    suspend operator fun invoke(
        fulName: String,
        email: String,
        password: String
    ): Result<RegisterResponse> {
        return repository.registerUser(
            Register(
                fullName = fulName,
                email = email,
                password = password
            )
        )
    }
}
