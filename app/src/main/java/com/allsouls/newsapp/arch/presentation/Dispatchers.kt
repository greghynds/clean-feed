package com.allsouls.newsapp.arch.presentation

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlin.coroutines.CoroutineContext

interface Dispatchers {
    val io: CoroutineDispatcher
    val main: CoroutineDispatcher
}

fun CoroutineDispatcher.withErrorHandler(doOnError: (Throwable) -> Unit): CoroutineContext {
    return this + CoroutineExceptionHandler { _, error -> doOnError(error) }
}