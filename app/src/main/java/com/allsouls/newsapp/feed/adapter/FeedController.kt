package com.allsouls.newsapp.feed.adapter

import com.allsouls.newsapp.arch.domain.InputPort
import com.allsouls.newsapp.arch.domain.Params
import com.allsouls.newsapp.arch.domain.Params.None
import com.allsouls.newsapp.arch.adapter.Controller
import com.allsouls.newsapp.arch.presentation.Dispatchers
import kotlinx.coroutines.launch

class FeedController(
    private val fetchFeed: InputPort<Params>,
    dispatchers: Dispatchers
) : Controller(dispatchers) {

    fun fetchFeed() = launch {
        fetchFeed.send(params = None)
    }
}