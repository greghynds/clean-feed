package com.allsouls.newsapp.feed.adapter

import com.allsouls.newsapp.arch.domain.InputPort
import com.allsouls.newsapp.arch.adapter.Controller
import com.allsouls.newsapp.arch.presentation.Dispatchers
import com.allsouls.newsapp.tracking.domain.Event
import com.allsouls.newsapp.tracking.domain.TrackEventPort
import kotlinx.coroutines.launch

class FeedTrackingController(
    private val input: TrackEventPort,
    dispatchers: Dispatchers
) : Controller(dispatchers) {

    fun resume() = launch {
        input.send(Event.Display(SCREEN_NAME))
    }

    companion object {
        private const val SCREEN_NAME = "feed"
    }
}