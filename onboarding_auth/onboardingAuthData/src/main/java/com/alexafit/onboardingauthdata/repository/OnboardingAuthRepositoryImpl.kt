package com.alexafit.onboardingauthdata.repository

import com.alexafit.onboardingauthdata.model.remote.Login
import com.alexafit.onboardingauthdata.model.remote.LoginResponse
import com.alexafit.onboardingauthdata.model.remote.Register
import com.alexafit.onboardingauthdata.model.remote.RegisterResponse
import com.alexafit.onboardingauthdata.remote.TaskyApi

class OnboardingAuthRepositoryImpl(
    private val taskyApi: TaskyApi
) : OnboardingAuthRepository {
    override suspend fun registerUser(registerBody: Register): Result<RegisterResponse> {
        val registerResponse = taskyApi.registerUser(register = registerBody)
        return if (registerResponse.isSuccessful) {
            Result.success(RegisterResponse("Success"))
        } else {
            Result.failure(Throwable(message = registerResponse.body()?.message))
        }
    }

    override suspend fun loginUser(loginBody: Login): Result<LoginResponse> {
        val loginResponse = taskyApi.loginUser(login = loginBody)
        return if (loginResponse.isSuccessful) {
            val loginResponseBody = loginResponse.body()
            if (loginResponseBody != null) {
                Result.success(loginResponseBody)
            } else {
                Result.failure(Throwable(message = loginResponse.message()))
            }
        } else {
            Result.failure(Throwable(message = loginResponse.message()))
        }
    }
}
