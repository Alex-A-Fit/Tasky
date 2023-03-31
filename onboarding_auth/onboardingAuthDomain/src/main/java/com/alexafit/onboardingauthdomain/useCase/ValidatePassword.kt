package com.alexafit.onboardingauthdomain.useCase

import com.alexafit.onboardingauthdomain.model.domain.mapper.ValidatePasswordResult
import javax.inject.Inject

class ValidatePassword @Inject constructor() {
    operator fun invoke(password: String): ValidatePasswordResult {
        val hasLowercaseLetter = containsLowercaseLetter(password)
        val hasUppercaseLetter = containsUppercaseLetter(password)
        val hasDigit = containsDigit(password)
        val isValidPassword = (hasLowercaseLetter && hasUppercaseLetter && hasDigit)

        return ValidatePasswordResult(
            userPassword = password,
            validPassword = isValidPassword
        )
    }

    private fun containsLowercaseLetter(password: String): Boolean {
        return password.any { it.isLowerCase() }
    }

    private fun containsUppercaseLetter(password: String): Boolean {
        return password.any { it.isUpperCase() }
    }

    private fun containsDigit(password: String): Boolean {
        return password.any { it.isDigit() }
    }
}
