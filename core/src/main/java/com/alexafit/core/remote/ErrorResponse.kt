package com.alexafit.core.remote

import com.squareup.moshi.Json
import com.squareup.moshi.Moshi
import okhttp3.ResponseBody
import retrofit2.Response

data class ErrorResponse(
    @Json(name = "message")
    val message: String? = null
)

fun handleErrorResponse(response: Response<ResponseBody>): Result<Unit> {
    val errorJson = response.errorBody()?.toString()
    val moshiAdapter = Moshi.Builder().build().adapter(ErrorResponse::class.java)
    return Result.failure(Throwable(message = (errorJson?.let { moshiAdapter.fromJson(it)?.message }) ?: "Something went wrong with our servers. Please try again."))
}
