package com.allsouls.newsapp.feed.ui

import com.allsouls.newsapp.feed.domain.entity.Headline

class HeadlinePresenter(private val view: HeadlineView) {

    fun render(headline: Headline) {
        view.setTitle(headline.headline)
        view.setDate(formatDate(headline.updated))
        view.setIntroduction(headline.introduction)
    }
}