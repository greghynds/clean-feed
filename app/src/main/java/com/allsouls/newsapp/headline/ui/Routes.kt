package com.allsouls.newsapp.headline.ui

import com.allsouls.newsapp.headline.domain.entity.Headline
import com.gwh.routes.Route

private const val KEY_HEADLINE = "HEADLINE"

fun createHeadlineRoute(headline: Headline): Route {
    return Route.to(HeadlineActivity::class.java)
        .param(KEY_HEADLINE, headline)
        .create()
}