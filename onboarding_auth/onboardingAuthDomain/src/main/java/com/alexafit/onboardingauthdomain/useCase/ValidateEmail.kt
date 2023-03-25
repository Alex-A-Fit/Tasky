package com.alexafit.onboardingauthdomain.useCase

class ValidateEmail {

    operator fun invoke(email: String): Pair<String, Boolean> {
        return when (email.isBlank()) {
            true -> Pair(email.trim(), false)
            false -> {
                val trimmedEmail = email.trimEnd()
                trimmedEmail.isEmailValid()
            }
        }
    }

    private fun String.isEmailValid(): Pair<String, Boolean> {
        val isValid = android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()
        return Pair(this, isValid)
    }
}
