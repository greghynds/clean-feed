package com.allsouls.newsapp.arch.data

import retrofit2.Response
import kotlin.Result.Companion.failure
import kotlin.Result.Companion.success

fun <R> Response<R>.toResult(): Result<R> {
    return when {
        isSuccessful -> {
            when (val body = body()) {
                null -> failure(NullPointerException("Response body is null."))
                else -> success(body)
            }
        }
        else -> failure(ApiError(code(), errorBody()?.string()))
    }
}