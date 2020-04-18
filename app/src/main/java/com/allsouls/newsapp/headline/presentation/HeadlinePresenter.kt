package com.allsouls.newsapp.headline.presentation

import com.allsouls.newsapp.arch.presentation.formatDate
import com.allsouls.newsapp.headline.domain.entity.Headline

class HeadlinePresenter(private val view: HeadlineView) {

    fun render(headline: Headline) {
        view.setTitle(headline.headline)
        view.setDate(formatDate(headline.updated))
        view.setIntroduction(headline.introduction)
    }
}