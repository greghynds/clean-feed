package com.allsouls.newsapp.feed.presentation

import com.allsouls.newsapp.arch.presentation.Action

const val LOAD_FEED = "LOAD_FEED"

fun createLoadFeedAction() = Action(LOAD_FEED)