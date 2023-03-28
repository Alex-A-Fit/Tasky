package com.alexafit.onboardingauthdata.local.util.passwordvalidator

import javax.inject.Inject

class PasswordPatternValidatorImpl @Inject constructor() : PasswordPatternValidator {
    override fun containsLowercaseLetter(password: String): Boolean {
        return password.any { it.isLowerCase() }
    }

    override fun containsUppercaseLetter(password: String): Boolean {
        return password.any { it.isUpperCase() }
    }

    override fun containsDigit(password: String): Boolean {
        return password.any { it.isDigit() }
    }
}
