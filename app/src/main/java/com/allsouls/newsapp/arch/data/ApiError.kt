package com.allsouls.newsapp.arch.data

class ApiError(val code: Int, message: String?) : Throwable(message)