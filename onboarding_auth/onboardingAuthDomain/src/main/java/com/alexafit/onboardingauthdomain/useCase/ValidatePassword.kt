package com.alexafit.onboardingauthdomain.useCase

import com.alexafit.onboardingauthdata.local.util.passwordvalidator.PasswordPatternValidator
import com.alexafit.onboardingauthdomain.model.domain.mapper.ValidatePasswordResult
import javax.inject.Inject

class ValidatePassword @Inject constructor(
    private val passwordPatternValidator: PasswordPatternValidator
) {
    operator fun invoke(password: String): ValidatePasswordResult {
        val hasLowercaseLetter = passwordPatternValidator.containsLowercaseLetter(password)
        val hasUppercaseLetter = passwordPatternValidator.containsUppercaseLetter(password)
        val hasDigit = passwordPatternValidator.containsDigit(password)
        val isValidPassword = (hasLowercaseLetter && hasUppercaseLetter && hasDigit)

        return ValidatePasswordResult(
            userPassword = password,
            validPassword = isValidPassword
        )
    }
}
