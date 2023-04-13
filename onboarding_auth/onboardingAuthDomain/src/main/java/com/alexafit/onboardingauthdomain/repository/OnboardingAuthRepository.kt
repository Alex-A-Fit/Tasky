package com.alexafit.onboardingauthdomain.repository

import com.alexafit.onboardingauthdomain.model.remote.LoginUser
import com.alexafit.onboardingauthdomain.model.remote.RegisterUser
import kotlinx.coroutines.flow.Flow

interface OnboardingAuthRepository {

    suspend fun registerUser(
        registerUser: RegisterUser
    ): Result<Unit>

    suspend fun checkAuthentication(): Flow<Result<Unit>>

    suspend fun loginUser(
        loginUser: LoginUser
    ): Result<String?>

    suspend fun setDataStoreAuthKey(authToken: String)
}
