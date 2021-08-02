package com.allsouls.newsapp.feed.presentation

import com.allsouls.newsapp.arch.presentation.Action
import com.allsouls.newsapp.feed.presentation.FeedModel.Companion.LOAD_FEED
import com.allsouls.newsapp.feed.presentation.FeedModel.Companion.SELECT_HEADLINE
import com.allsouls.newsapp.headline.domain.entity.Headline


fun createLoadFeedAction() = Action(LOAD_FEED)
fun createSelectHeadlineAction(headline: Headline) = Action(SELECT_HEADLINE, headline)