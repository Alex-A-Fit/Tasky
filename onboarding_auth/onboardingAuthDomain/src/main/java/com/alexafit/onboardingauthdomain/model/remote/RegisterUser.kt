package com.alexafit.onboardingauthdomain.model.remote

data class RegisterUser(
    val fullName: String,
    val email: String,
    val password: String
)
