package com.allsouls.newsapp.arch.domain

import kotlin.Result.Companion.failure

abstract class UseCase<In, Out> {

    protected abstract suspend fun operation(params: In): Result<Out>

    suspend fun execute(params: In): Result<Out> {
        return try {
            operation(params)
        } catch (error: Throwable) {
            failure(error)
        }
    }
}