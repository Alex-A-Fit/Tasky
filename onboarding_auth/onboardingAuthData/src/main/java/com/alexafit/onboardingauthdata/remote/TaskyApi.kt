package com.alexafit.onboardingauthdata.remote

import com.alexafit.onboardingauthdata.BuildConfig
import com.alexafit.onboardingauthdata.model.remote.Login
import com.alexafit.onboardingauthdata.model.remote.LoginResponse
import com.alexafit.onboardingauthdata.model.remote.Register
import com.alexafit.onboardingauthdata.model.remote.RegisterResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface TaskyApi {
    @POST("register")
    suspend fun registerUser(
        @Header("x-api-key") apiToken: String = BuildConfig.TASKY_API_KEY,
        @Header("Content-Type") contentType: String = "application/json",
        @Body register: Register
    ): Response<RegisterResponse>

    @POST("login")
    suspend fun loginUser(
        @Header("x-api-key") apiToken: String = BuildConfig.TASKY_API_KEY,
        @Header("Content-Type") contentType: String = "application/json",
        @Body login: Login
    ): Response<LoginResponse>

    companion object {
        const val BASE_URL = "https://tasky.pl-coding.com/"
    }
}
