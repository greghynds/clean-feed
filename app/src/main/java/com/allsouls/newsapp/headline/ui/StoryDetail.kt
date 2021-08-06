package com.allsouls.newsapp.headline.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.allsouls.newsapp.arch.presentation.formatDate
import com.allsouls.newsapp.headline.domain.entity.Headline


@Composable
fun StoryDetail(headline: Headline) {
    Column {
        Text(headline.headline)
        Text(headline.introduction)
        Text(formatDate(headline.updated))
    }
}