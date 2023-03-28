package com.alexafit.onboardingauthdata.local.util.passwordvalidator

interface PasswordPatternValidator {
    fun containsLowercaseLetter(password: String): Boolean
    fun containsUppercaseLetter(password: String): Boolean
    fun containsDigit(password: String): Boolean
}
