package com.alexafit.onboardingauthdata.repository

import com.alexafit.core.local.datastore.PreferenceStorage
import com.alexafit.core.remote.handleErrorResponse
import com.alexafit.onboardingauthdata.mapper.mapToDto
import com.alexafit.onboardingauthdata.remote.TaskyApi
import com.alexafit.onboardingauthdomain.model.remote.LoginUser
import com.alexafit.onboardingauthdomain.model.remote.RegisterUser
import com.alexafit.onboardingauthdomain.repository.OnboardingAuthRepository

class OnboardingAuthRepositoryImpl(
    private val taskyApi: TaskyApi,
    private val dataStore: PreferenceStorage
) : OnboardingAuthRepository {
    override suspend fun registerUser(registerUser: RegisterUser): Result<Unit> {
        val registerResponse = taskyApi.registerUser(registerDto = registerUser.mapToDto())
        return try {
            if (registerResponse.isSuccessful) {
                Result.success(Unit)
            } else {
                handleErrorResponse(response = registerResponse)
            }
        } catch (e: Exception) {
            Result.failure(Throwable(message = "Error in api call"))
        }
    }

    override suspend fun loginUser(loginUser: LoginUser): Result<String?> {
        val loginResponse = taskyApi.loginUser(loginDto = loginUser.mapToDto())
        return if (loginResponse.isSuccessful && loginResponse.body() != null) {
            Result.success(loginResponse.body()?.token)
        } else {
            Result.failure(Throwable(message = loginResponse.message()))
        }
    }

    override suspend fun setDataStoreAuthKey(authToken: String) {
        dataStore.setAuthorizationKey(authToken)
    }
}
