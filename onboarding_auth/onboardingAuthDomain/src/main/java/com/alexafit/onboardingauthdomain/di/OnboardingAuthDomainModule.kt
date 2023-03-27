package com.alexafit.onboardingauthdomain.di

import com.alexafit.onboardingauthdomain.useCase.OnboardingAuthUseCase
import com.alexafit.onboardingauthdomain.useCase.ValidateEmail
import com.alexafit.onboardingauthdomain.useCase.ValidatePassword
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object OnboardingAuthDomainModule {

    @ViewModelScoped
    @Provides
    fun provideOnboardingAuthUseCase(): OnboardingAuthUseCase {
        return OnboardingAuthUseCase(
            validateEmail = ValidateEmail(),
            validatePassword = ValidatePassword()
        )
    }
}
