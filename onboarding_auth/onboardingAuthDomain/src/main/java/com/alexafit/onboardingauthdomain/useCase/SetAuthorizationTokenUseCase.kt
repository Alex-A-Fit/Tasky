package com.alexafit.onboardingauthdomain.useCase

import com.alexafit.onboardingauthdata.repository.OnboardingAuthRepository

class SetAuthorizationTokenUseCase(
    private val repository: OnboardingAuthRepository
) {
    suspend operator fun invoke(authToken: String?) {
        repository.setDataStoreAuthKey(authToken)
    }
}
