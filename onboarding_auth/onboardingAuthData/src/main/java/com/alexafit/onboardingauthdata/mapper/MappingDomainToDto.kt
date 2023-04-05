package com.alexafit.onboardingauthdata.mapper

import com.alexafit.onboardingauthdata.remote.dto.LoginDto
import com.alexafit.onboardingauthdata.remote.dto.RegisterDto
import com.alexafit.onboardingauthdomain.model.remote.LoginUser
import com.alexafit.onboardingauthdomain.model.remote.RegisterUser

fun RegisterUser.mapToDto(): RegisterDto {
    return RegisterDto(
        fullName = this.fullName,
        email = this.email,
        password = this.password
    )
}

fun LoginUser.mapToDto(): LoginDto {
    return LoginDto(
        email = this.email,
        password = this.password
    )
}
