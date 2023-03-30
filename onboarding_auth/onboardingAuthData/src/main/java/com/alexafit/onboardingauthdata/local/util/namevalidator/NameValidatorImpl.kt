package com.alexafit.onboardingauthdata.local.util.namevalidator

import javax.inject.Inject

class NameValidatorImpl @Inject constructor() : NameValidator {
    override fun isValidName(name: String): Boolean {
        return name.length in 4..50
    }
}
