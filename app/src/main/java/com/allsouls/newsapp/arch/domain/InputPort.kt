package com.allsouls.newsapp.arch.domain

interface InputPort<In> {
    suspend fun send(params: In)
}