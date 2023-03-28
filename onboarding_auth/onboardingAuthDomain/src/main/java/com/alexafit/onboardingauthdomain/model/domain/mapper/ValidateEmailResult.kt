package com.alexafit.onboardingauthdomain.model.domain.mapper

data class ValidateEmailResult(
    val userEmail: String = "",
    val validEmail: Boolean = false
)
