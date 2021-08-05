package com.allsouls.newsapp.feed.presentation

import com.allsouls.newsapp.arch.presentation.Action
import com.allsouls.newsapp.headline.domain.entity.Headline

const val LOAD_FEED = "LOAD_FEED"
const val SELECT_HEADLINE = "SELECT_HEADLINE"

fun createLoadFeedAction() = Action(LOAD_FEED)
fun createSelectHeadlineAction(headline: Headline) = Action(SELECT_HEADLINE, headline)