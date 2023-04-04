package com.alexafit.onboardingauthdata.repository

import com.alexafit.onboardingauthdata.model.remote.LoginDto
import com.alexafit.onboardingauthdata.model.remote.RegisterDto

interface OnboardingAuthRepository {

    suspend fun registerUser(
        registerDtoBody: RegisterDto
    ): Result<String?>

    suspend fun loginUser(
        loginDtoBody: LoginDto
    ): Result<String?>

    suspend fun setDataStoreAuthKey(authToken: String?)
}
