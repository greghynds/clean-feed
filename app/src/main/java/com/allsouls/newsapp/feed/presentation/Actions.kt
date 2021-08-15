package com.allsouls.newsapp.feed.presentation

import com.allsouls.newsapp.feed.domain.entity.Feed
import xyz.gwh.redux.Action

const val LOAD_FEED = "LOAD_FEED"
const val LOAD_FEED_SUCCESS = "LOAD_FEED_SUCCESS"
const val LOAD_FEED_FAILURE = "LOAD_FEED_FAILURE"
const val SELECT_HEADLINE = "SELECT_HEADLINE"

fun createLoadFeedAction() = Action(LOAD_FEED)
fun createLoadFeedSuccessAction(feed: Feed) = Action(LOAD_FEED_SUCCESS, feed)
fun createLoadFeedFailureAction(error: Throwable) = Action(LOAD_FEED_FAILURE, error)