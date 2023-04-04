package com.alexafit.onboardingauthdata.repository

import com.alexafit.core.local.datastore.PreferenceStorage
import com.alexafit.onboardingauthdata.mapper.mapResponseToDomain
import com.alexafit.onboardingauthdata.model.remote.LoginDto
import com.alexafit.onboardingauthdata.model.remote.RegisterDto
import com.alexafit.onboardingauthdata.remote.TaskyApi

class OnboardingAuthRepositoryImpl(
    private val taskyApi: TaskyApi,
    private val dataStore: PreferenceStorage
) : OnboardingAuthRepository {
    override suspend fun registerUser(registerDtoBody: RegisterDto): Result<String?> {
        val registerResponse = taskyApi.registerUser(registerDto = registerDtoBody)
        return if (registerResponse.isSuccessful) {
            Result.success(null)
        } else {
            Result.failure(Throwable(message = registerResponse.body()?.message))
        }
    }

    override suspend fun loginUser(loginDtoBody: LoginDto): Result<String?> {
        val loginResponse = taskyApi.loginUser(loginDto = loginDtoBody)
        return if (loginResponse.isSuccessful && loginResponse.body() != null) {
            Result.success(loginResponse.body()?.mapResponseToDomain())
        } else {
            Result.failure(Throwable(message = loginResponse.message()))
        }
    }

    override suspend fun setDataStoreAuthKey(authToken: String?) {
        dataStore.setAuthorizationKey(authToken)
    }
}
