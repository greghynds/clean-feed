package com.allsouls.newsapp.util

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class TestDispatchers : com.allsouls.newsapp.arch.async.Dispatchers {
    override val main: CoroutineDispatcher get() = Dispatchers.Unconfined
    override val io: CoroutineDispatcher get() = Dispatchers.Unconfined
}