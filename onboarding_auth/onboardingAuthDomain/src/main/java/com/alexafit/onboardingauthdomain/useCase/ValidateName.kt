package com.alexafit.onboardingauthdomain.useCase

import com.alexafit.onboardingauthdata.local.util.namevalidator.NameValidator
import com.alexafit.onboardingauthdomain.model.domain.mapper.ValidateNameResult
import javax.inject.Inject

class ValidateName @Inject constructor(
    private val nameValidator: NameValidator
) {
    operator fun invoke(name: String): ValidateNameResult {
        val isValidName = nameValidator.isValidName(name)
        return if (name.isBlank()) {
            ValidateNameResult(
                userName = name.trim(),
                isValidName
            )
        } else {
            ValidateNameResult(
                userName = name,
                isValidName
            )
        }
    }
}
