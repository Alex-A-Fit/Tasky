package com.alexafit.onboardingauthdomain.util.emailpatternvalidator

interface EmailPatternValidator {
    fun isValidEmailPattern(email: String): Boolean
}
