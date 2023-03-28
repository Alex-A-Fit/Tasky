package com.alexafit.onboardingauthdomain.di

import com.alexafit.onboardingauthdata.local.util.emailpatternvalidator.EmailPatternValidator
import com.alexafit.onboardingauthdata.local.util.emailpatternvalidator.EmailPatternValidatorImpl
import com.alexafit.onboardingauthdata.local.util.passwordvalidator.PasswordPatternValidator
import com.alexafit.onboardingauthdata.local.util.passwordvalidator.PasswordPatternValidatorImpl
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
    fun provideOnboardingAuthUseCase(
        emailPatternValidator: EmailPatternValidator,
        passwordPatternValidator: PasswordPatternValidator
    ): OnboardingAuthUseCase {
        return OnboardingAuthUseCase(
            validateEmail = ValidateEmail(emailPatternValidator = emailPatternValidator),
            validatePassword = ValidatePassword(passwordPatternValidator = passwordPatternValidator)
        )
    }

    @ViewModelScoped
    @Provides
    fun providesEmailPatternValidator(
        emailPatternValidatorImpl: EmailPatternValidatorImpl
    ): EmailPatternValidator = emailPatternValidatorImpl

    @ViewModelScoped
    @Provides
    fun providesPasswordValidator(
        passwordPatternValidatorImpl: PasswordPatternValidatorImpl
    ): PasswordPatternValidator = passwordPatternValidatorImpl
}
