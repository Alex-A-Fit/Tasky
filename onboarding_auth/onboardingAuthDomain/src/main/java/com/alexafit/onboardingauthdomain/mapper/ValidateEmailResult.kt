package com.alexafit.onboardingauthdomain.mapper

data class ValidateEmailResult(
    val userEmail: String = "",
    val validEmail: Boolean = false
)
