package com.allsouls.newsapp.headline.presentation

import com.allsouls.newsapp.arch.adapter.Controller
import com.allsouls.newsapp.arch.presentation.Dispatchers
import com.allsouls.newsapp.tracking.domain.Event
import com.allsouls.newsapp.tracking.domain.TrackEventPort
import kotlinx.coroutines.launch

class HeadlineController(
    private val port: TrackEventPort,
    dispatchers: Dispatchers
) : Controller(dispatchers) {

    fun resume() = launch {
        port.send(Event.Display(SCREEN_NAME))
    }

    companion object {
        private const val SCREEN_NAME = "headline"
    }
}