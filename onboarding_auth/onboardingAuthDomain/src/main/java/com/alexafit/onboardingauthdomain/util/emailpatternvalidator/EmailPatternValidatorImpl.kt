package com.alexafit.onboardingauthdomain.util.emailpatternvalidator

import android.util.Patterns
import javax.inject.Inject

class EmailPatternValidatorImpl @Inject constructor() : EmailPatternValidator {

    override fun isValidEmailPattern(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}
