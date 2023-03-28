package com.alexafit.onboardingauthdata.local.util.emailpatternvalidator

interface EmailPatternValidator {
    fun isValidEmailPattern(email: String): Boolean
}
