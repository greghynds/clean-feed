package com.allsouls.newsapp.feed.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType.Companion.Sp
import androidx.compose.ui.unit.dp
import com.allsouls.newsapp.arch.presentation.formatDate
import com.allsouls.newsapp.headline.domain.entity.Headline


@OptIn(ExperimentalUnitApi::class)
@Composable
fun HeadlineCard(
    headline: Headline,
    onHeadlineClick: (Headline) -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onHeadlineClick(headline) }
    ) {
        Text(
            headline.headline,
            fontSize = TextUnit(18f, Sp),
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(16.dp, 16.dp, 16.dp)
        )
        Text(
            headline.introduction,
            fontSize = TextUnit(14f, Sp),
            fontStyle = FontStyle.Italic,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(16.dp, 16.dp, 16.dp)
        )
        Text(
            formatDate(headline.updated),
            fontSize = TextUnit(12f, Sp),
            modifier = Modifier
                .padding(16.dp)
        )
    }
}