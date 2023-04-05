package com.alexafit.onboardingauthdomain.di

import com.alexafit.onboardingauthdomain.repository.OnboardingAuthRepository
import com.alexafit.onboardingauthdomain.useCase.LoginUserUseCase
import com.alexafit.onboardingauthdomain.useCase.OnboardingAuthUseCase
import com.alexafit.onboardingauthdomain.useCase.RegisterUserUseCase
import com.alexafit.onboardingauthdomain.useCase.ValidateEmail
import com.alexafit.onboardingauthdomain.useCase.ValidateName
import com.alexafit.onboardingauthdomain.useCase.ValidatePassword
import com.alexafit.onboardingauthdomain.util.emailpatternvalidator.EmailPatternValidator
import com.alexafit.onboardingauthdomain.util.emailpatternvalidator.EmailPatternValidatorImpl
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
        repository: OnboardingAuthRepository
    ): OnboardingAuthUseCase {
        return OnboardingAuthUseCase(
            validateEmail = ValidateEmail(emailPatternValidator = emailPatternValidator),
            validatePassword = ValidatePassword(),
            validateName = ValidateName(),
            loginUserUseCase = LoginUserUseCase(repository),
            registerUserUseCase = RegisterUserUseCase(repository)
        )
    }

    @ViewModelScoped
    @Provides
    fun providesEmailPatternValidator(
        emailPatternValidatorImpl: EmailPatternValidatorImpl
    ): EmailPatternValidator = emailPatternValidatorImpl
}
