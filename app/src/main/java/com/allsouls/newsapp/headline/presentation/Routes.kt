package com.allsouls.newsapp.headline.presentation

import com.allsouls.newsapp.headline.HeadlineActivity
import com.allsouls.newsapp.headline.domain.entity.Headline
import com.gwh.routes.Route

const val PARAM_HEADLINE = "HEADLINE"

fun createHeadlineRoute(headline: Headline): Route {
    return Route.to(HeadlineActivity::class.java)
        .param(PARAM_HEADLINE, headline)
        .create()
}