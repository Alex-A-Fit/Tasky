package com.alexafit.onboardingauthdata.mapper

import com.alexafit.onboardingauthdata.model.remote.LoginResponse

fun LoginResponse.mapResponseToDomain(): String? {
    return this.token
}
