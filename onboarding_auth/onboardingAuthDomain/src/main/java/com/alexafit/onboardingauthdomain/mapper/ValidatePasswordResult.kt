package com.alexafit.onboardingauthdomain.mapper

data class ValidatePasswordResult(
    val userPassword: String = "",
    val validPassword: Boolean = false
)
