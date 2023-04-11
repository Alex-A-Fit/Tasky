package com.alexafit.onboardingauthdata.repository

import com.alexafit.core.local.datastore.PreferenceStorage
import com.alexafit.onboardingauthdata.mapper.mapToDto
import com.alexafit.onboardingauthdata.model.remote.RegisterResponse
import com.alexafit.onboardingauthdata.remote.TaskyApi
import com.alexafit.onboardingauthdomain.model.remote.LoginUser
import com.alexafit.onboardingauthdomain.model.remote.RegisterUser
import com.alexafit.onboardingauthdomain.repository.OnboardingAuthRepository
import com.squareup.moshi.Moshi

class OnboardingAuthRepositoryImpl(
    private val taskyApi: TaskyApi,
    private val dataStore: PreferenceStorage
) : OnboardingAuthRepository {
    override suspend fun registerUser(registerUser: RegisterUser): Result<Unit> {
        val registerResponse = taskyApi.registerUser(registerDto = registerUser.mapToDto())
        try {
            return if (registerResponse.isSuccessful) {
                Result.success(Unit)
            } else {
                val errorJson = registerResponse.errorBody()?.string()
                val moshiAdapter = Moshi.Builder().build().adapter(RegisterResponse::class.java)
                return Result.failure(Throwable(message = errorJson?.let { moshiAdapter.fromJson(it)?.message }))
            }
        } catch (e: Exception) {
            return Result.failure(Throwable(message = "Error in api call"))
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
