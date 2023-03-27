package com.alexafit.onboardingauthdomain.useCase

import com.alexafit.onboardingauthdomain.model.domain.mapper.ValidateEmailResult

class ValidateEmail {
    operator fun invoke(email: String): ValidateEmailResult {
        return if (email.isBlank()) {
            ValidateEmailResult(
                emailResult = email.trim(),
                validEmail = false
            )
        } else {
            val trimmedEmail = email.trimEnd()
            trimmedEmail.isEmailValid()
        }
    }
}

private fun String.isEmailValid(): ValidateEmailResult {
    val isValid = android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()
    return ValidateEmailResult(
        emailResult = this,
        validEmail = isValid
    )
}
