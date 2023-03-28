package com.alexafit.onboardingauthdomain.model.domain.mapper

data class ValidatePasswordResult(
    val userPassword: String = "",
    val validPassword: Boolean = false
)
