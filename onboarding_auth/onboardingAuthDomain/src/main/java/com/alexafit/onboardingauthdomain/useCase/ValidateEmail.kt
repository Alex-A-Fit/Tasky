package com.alexafit.onboardingauthdomain.useCase

import com.alexafit.onboardingauthdomain.mapper.ValidateEmailResult
import com.alexafit.onboardingauthdomain.util.emailpatternvalidator.EmailPatternValidator
import javax.inject.Inject

class ValidateEmail @Inject constructor(
    private val emailPatternValidator: EmailPatternValidator
) {
    operator fun invoke(email: String): ValidateEmailResult {
        return if (email.isBlank()) {
            ValidateEmailResult(
                userEmail = email.trim(),
                validEmail = false
            )
        } else {
            val trimmedEmail = email.trimEnd()
            val isValid = emailPatternValidator.isValidEmailPattern(trimmedEmail)
            return ValidateEmailResult(
                userEmail = trimmedEmail,
                validEmail = isValid
            )
        }
    }
}
