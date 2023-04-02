package com.alexafit.onboardingauthdata.mapper

import com.alexafit.onboardingauthdata.model.local.Login
import com.alexafit.onboardingauthdata.model.remote.LoginResponse

fun LoginResponse.mapResponseToDomain(): Login {
    return Login(
        token = this.token
    )
}
