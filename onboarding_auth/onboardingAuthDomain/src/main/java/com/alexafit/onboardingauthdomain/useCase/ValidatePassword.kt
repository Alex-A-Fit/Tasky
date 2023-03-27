package com.alexafit.onboardingauthdomain.useCase

import com.alexafit.onboardingauthdomain.model.domain.mapper.ValidatePasswordResult

class ValidatePassword {
    operator fun invoke(password: String): ValidatePasswordResult {
        return if (password.isBlank()) {
            ValidatePasswordResult(
                passwordResult = password.trim(),
                validPassword = false
            )
        } else {
            val trimmedPassword = password.trimEnd()
            trimmedPassword.isPasswordValid()
        }
    }
}

private fun String.isPasswordValid(): ValidatePasswordResult {
    return if (this.length >= 9) {
        val hasLowercaseLetter = this.any { it.isLowerCase() }
        val hasUppercaseLetter = this.any { it.isUpperCase() }
        val hasDigit = this.any { it.isDigit() }

        val isValidPassword = (hasLowercaseLetter && hasUppercaseLetter && hasDigit)
        ValidatePasswordResult(
            passwordResult = this,
            validPassword = isValidPassword
        )
    } else {
        ValidatePasswordResult(
            passwordResult = this,
            validPassword = false
        )
    }
}
