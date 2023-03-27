package com.alexafit.onboardingauthdomain.useCase

class ValidatePassword {

    operator fun invoke(password: String): Pair<String, Boolean> {
        return when (password.isBlank()) {
            true -> Pair(password.trim(), false)
            false -> {
                val trimmedPassword = password.trimEnd()
                trimmedPassword.isPasswordValid()
            }
        }
    }

    private fun String.isPasswordValid(): Pair<String, Boolean> {
        return if (this.length >= 9) {
            val hasLowercaseLetter = this.any { it.isLowerCase() }
            val hasUppercaseLetter = this.any { it.isUpperCase() }
            val hasDigit = this.any { it.isDigit() }
            val hasNoSpecialCharacter = this.all { it.isLetterOrDigit() }

            val isValidPassword = (hasLowercaseLetter && hasUppercaseLetter && hasDigit && hasNoSpecialCharacter)
            Pair(this, isValidPassword)
        } else {
            Pair(this, false)
        }
    }
}
