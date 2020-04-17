package com.allsouls.newsapp.arch.adapter


import com.allsouls.newsapp.arch.presentation.Dispatchers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

abstract class Presenter(private val dispatchers: Dispatchers) : CoroutineScope {

    private val job = Job()

    override val coroutineContext: CoroutineContext
        get() = dispatchers.main + job
}