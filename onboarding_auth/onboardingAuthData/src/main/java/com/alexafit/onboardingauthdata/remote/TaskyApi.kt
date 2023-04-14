package com.alexafit.onboardingauthdata.remote

import com.alexafit.onboardingauthdata.model.remote.LoginResponse
import com.alexafit.onboardingauthdata.remote.dto.LoginDto
import com.alexafit.onboardingauthdata.remote.dto.RegisterDto
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface TaskyApi {
    @POST("register")
    suspend fun registerUser(
        @Body registerDto: RegisterDto
    ): Response<ResponseBody>

    @GET("authenticate")
    suspend fun checkAuthentication(
        @Header("Authorization") token: String
    ): Response<ResponseBody>

    @POST("login")
    suspend fun loginUser(
        @Body loginDto: LoginDto
    ): Response<LoginResponse>

    companion object {
        const val BASE_URL = "https://tasky.pl-coding.com/"
    }
}
