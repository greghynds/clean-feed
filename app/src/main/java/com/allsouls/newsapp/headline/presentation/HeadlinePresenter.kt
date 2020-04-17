package com.allsouls.newsapp.headline.presentation

import com.allsouls.newsapp.arch.presentation.Dispatchers
import com.allsouls.newsapp.arch.adapter.Presenter
import com.allsouls.newsapp.arch.presentation.formatDate
import com.allsouls.newsapp.headline.domain.entity.Headline
import com.allsouls.newsapp.tracking.domain.Event
import com.allsouls.newsapp.tracking.domain.TrackEvent
import kotlinx.coroutines.launch

class HeadlinePresenter(
    private val trackEvent: TrackEvent,
    dispatchers: Dispatchers,
    private val view: HeadlineView
) : Presenter(dispatchers) {

    fun render(headline: Headline) {
        view.setTitle(headline.headline)
        view.setDate(formatDate(headline.updated))
        view.setIntroduction(headline.introduction)
    }

    fun resume() = launch {
        trackEvent.send(Event.Display(SCREEN_NAME))
    }

    companion object {
        private const val SCREEN_NAME = "headline"
    }
}