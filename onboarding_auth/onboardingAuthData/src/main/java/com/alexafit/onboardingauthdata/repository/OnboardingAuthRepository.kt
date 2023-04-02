package com.alexafit.onboardingauthdata.repository

import com.alexafit.onboardingauthdata.model.local.Login
import com.alexafit.onboardingauthdata.model.local.Register
import com.alexafit.onboardingauthdata.model.remote.LoginDto
import com.alexafit.onboardingauthdata.model.remote.RegisterDto

interface OnboardingAuthRepository {

    suspend fun registerUser(
        registerDtoBody: RegisterDto
    ): Result<Register?>

    suspend fun loginUser(
        loginDtoBody: LoginDto
    ): Result<Login?>
}
