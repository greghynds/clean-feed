package com.allsouls.newsapp.feed.ui

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.allsouls.newsapp.headline.domain.entity.Headline


@Composable
fun HeadlinesList(headlines: List<Headline>) {
    LazyColumn {
        headlines.forEach { headline ->
            item {
                Text(text = headline.headline)
            }
        }
    }
}