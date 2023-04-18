package com.alexafit.onboardingauthdata.repository

import com.alexafit.core.local.datastore.PreferenceStorage
import com.alexafit.core.remote.handleErrorResponse
import com.alexafit.onboardingauthdata.mapper.mapToDto
import com.alexafit.onboardingauthdata.remote.TaskyApi
import com.alexafit.onboardingauthdomain.model.remote.LoginUser
import com.alexafit.onboardingauthdomain.model.remote.RegisterUser
import com.alexafit.onboardingauthdomain.repository.OnboardingAuthRepository
import kotlinx.coroutines.flow.firstOrNull

const val API_ERROR = "Error in api call"

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
            Result.failure(Throwable(message = API_ERROR))
        }
    }

    override suspend fun checkAuthentication(): Result<Unit> {
        return try {
            val authenticationResponse = taskyApi.checkAuthentication(
                ("Bearer " + (dataStore.authorizationKey.firstOrNull() ?: ""))
            )
            if (authenticationResponse.isSuccessful) {
                Result.success(Unit)
            } else {
                handleErrorResponse(response = authenticationResponse)
            }
        } catch (e: Exception) {
            Result.failure(Throwable(message = API_ERROR))
        }
    }

    override suspend fun loginUser(loginUser: LoginUser): Result<String?> {
        return try {
            val loginResponse = taskyApi.loginUser(loginDto = loginUser.mapToDto())
            return if (loginResponse.isSuccessful && loginResponse.body() != null) {
                val authToken = loginResponse.body()?.token
                val userName = loginResponse.body()?.fullName
                setDataStoreAuthKey(authToken)
                setDataStoreUserName(userName)
                Result.success(authToken)
            } else {
                Result.failure(Throwable(message = loginResponse.message()))
            }
        } catch (e: Exception) {
            Result.failure(Throwable(message = API_ERROR))
        }
    }

    private suspend fun setDataStoreAuthKey(authToken: String?) {
        if (authToken != null) {
            dataStore.setAuthorizationKey(authToken)
        }
    }

    private suspend fun setDataStoreUserName(userName: String?) {
        if (userName != null) {
            dataStore.setUserName(userName)
        }
    }
}
