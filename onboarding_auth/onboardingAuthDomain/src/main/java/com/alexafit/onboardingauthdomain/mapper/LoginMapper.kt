package com.alexafit.onboardingauthdomain.mapper

import com.alexafit.onboardingauthdata.model.remote.LoginDto
import com.alexafit.onboardingauthdomain.model.remote.LoginUser

fun LoginUser.mapToDto(): LoginDto {
    return LoginDto(
        email = this.email,
        password = this.password
    )
}
