package com.alexafit.onboardingauthdata.model.remote

data class LoginResponse(
    val token: String,
    val userId: String,
    val fullName: String
)
