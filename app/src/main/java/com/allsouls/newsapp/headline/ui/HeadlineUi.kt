package com.allsouls.newsapp.headline.ui

import androidx.compose.runtime.Composable
import com.allsouls.newsapp.feed.ui.ErrorScreen
import com.allsouls.newsapp.feed.ui.HeadlineCard
import com.allsouls.newsapp.headline.domain.entity.Headline


@Composable
fun HeadlineUi(headline: Headline?) {
    when {
        headline != null -> HeadlineCard(headline)
        else -> ErrorScreen()
    }
}