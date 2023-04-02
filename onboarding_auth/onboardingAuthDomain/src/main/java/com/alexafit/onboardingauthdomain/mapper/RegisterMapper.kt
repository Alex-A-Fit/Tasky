package com.alexafit.onboardingauthdomain.mapper

import com.alexafit.onboardingauthdata.model.remote.RegisterDto
import com.alexafit.onboardingauthdomain.model.remote.RegisterUser

fun RegisterUser.mapToDto(): RegisterDto {
    return RegisterDto(
        fullName = this.fullName,
        email = this.email,
        password = this.password
    )
}
