package com.allsouls.newsapp.arch.data

data class ApiError(
    val code: Int,
    override val message: String?
) : Throwable(message)