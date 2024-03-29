package com.allsouls.newsapp.arch.async

import kotlinx.coroutines.CoroutineDispatcher

class AndroidDispatchers : Dispatchers {
    override val io: CoroutineDispatcher get() = kotlinx.coroutines.Dispatchers.IO
    override val main: CoroutineDispatcher get() = kotlinx.coroutines.Dispatchers.Main
}