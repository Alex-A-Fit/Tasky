package com.alexafit.onboardingauthdata.repository

import com.alexafit.onboardingauthdata.model.remote.Login
import com.alexafit.onboardingauthdata.model.remote.LoginResponse
import com.alexafit.onboardingauthdata.model.remote.Register
import com.alexafit.onboardingauthdata.model.remote.RegisterResponse

interface OnboardingAuthRepository {

    suspend fun registerUser(
        registerBody: Register
    ): Result<RegisterResponse>

    suspend fun loginUser(
        loginBody: Login
    ): Result<LoginResponse>
}
