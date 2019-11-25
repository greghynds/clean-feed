package com.allsouls.newsapp.headline.presentation

import com.allsouls.newsapp.arch.presentation.formatDate
import com.allsouls.newsapp.headline.domain.entity.Headline

class HeadlineCardPresenter(private val view: HeadlineCardView) {

    fun bind(headline: Headline) {
        view.setTitle(headline.headline)
        view.setDate(formatDate(headline.updated))
    }
}