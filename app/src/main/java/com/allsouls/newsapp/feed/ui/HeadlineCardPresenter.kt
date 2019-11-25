package com.allsouls.newsapp.feed.ui

import com.allsouls.newsapp.feed.domain.entity.Headline

class HeadlineCardPresenter(private val view: HeadlineCardView) {

    fun bind(headline: Headline) {
        view.setTitle(headline.title)
        view.setDate(formatDate(headline.updated))
    }
}