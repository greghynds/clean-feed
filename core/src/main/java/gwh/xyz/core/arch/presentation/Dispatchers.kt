package gwh.xyz.core.arch.presentation

import kotlinx.coroutines.CoroutineDispatcher

interface Dispatchers {
    val io: CoroutineDispatcher
    val main: CoroutineDispatcher
}