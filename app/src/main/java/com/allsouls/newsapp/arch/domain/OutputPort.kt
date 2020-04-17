package com.allsouls.newsapp.arch.domain

interface OutputPort<Out> {
    suspend fun receive(result: Result<Out>)
}