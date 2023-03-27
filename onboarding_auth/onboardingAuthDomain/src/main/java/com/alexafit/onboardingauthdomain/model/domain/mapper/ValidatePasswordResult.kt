package com.alexafit.onboardingauthdomain.model.domain.mapper

data class ValidatePasswordResult(
    val passwordResult: String = "",
    val validPassword: Boolean = false
)
