package com.alexafit.onboardingauthdata.model.remote

import com.squareup.moshi.Json

data class RegisterResponse(
    @Json(name = "message")
    val message: String? = null
)
