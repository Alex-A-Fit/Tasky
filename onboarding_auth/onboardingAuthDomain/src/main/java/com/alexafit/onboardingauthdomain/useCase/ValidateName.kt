package com.alexafit.onboardingauthdomain.useCase

import com.alexafit.onboardingauthdomain.model.domain.mapper.ValidateNameResult
import javax.inject.Inject

class ValidateName @Inject constructor() {
    operator fun invoke(name: String): ValidateNameResult {
        return if (name.isBlank()) {
            ValidateNameResult(
                userName = name.trim(),
                validName = ( name.length in 4..50 )
            )
        } else {
            ValidateNameResult(
                userName = name,
                validName = ( name.length in 4..50 )
            )
        }
    }
}
