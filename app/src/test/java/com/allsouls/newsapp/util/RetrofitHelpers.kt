package com.allsouls.newsapp.util

import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Deferred
import okhttp3.MediaType
import okhttp3.ResponseBody
import retrofit2.Response

const val MEDIA_TYPE = "application/json; charset=UTF-8"

fun <T> createSuccessResponse(body: T): Response<T> {
    return Response.success(body)
}

fun <T> createErrorResponse(message: String, status: Int = 500): Response<T> {
    return Response.error(status, createResponseBody(message))
}

fun createResponseBody(body: String): ResponseBody {
    return TODO() //ResponseBody.create(MediaType.parse(MEDIA_TYPE), body)
}

fun <T> successResponse(body: T): Deferred<Response<T>> {
    return CompletableDeferred(createSuccessResponse(body))
}

fun <T> failureResponse(message: String, status: Int = 500): Deferred<Response<T>> {
    return CompletableDeferred(createErrorResponse(message, status))
}