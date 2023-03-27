package com.alexafit.onboardingauthdomain.model.domain.mapper

data class ValidateEmailResult(
    val emailResult: String = "",
    val validEmail: Boolean = false
)
