package com.allsouls.newsapp.headline.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.allsouls.newsapp.arch.presentation.formatDate
import com.allsouls.newsapp.headline.domain.entity.Headline


@OptIn(ExperimentalUnitApi::class)
@Composable
fun StoryDetail(headline: Headline) {
    Column {
        Text(
            headline.headline,
            fontSize = TextUnit(20f, TextUnitType.Sp),
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(16.dp, 16.dp, 16.dp)
        )
        Text(
            formatDate(headline.updated),
            fontSize = TextUnit(14f, TextUnitType.Sp),
            modifier = Modifier
                .padding(16.dp)
        )
        Text(
            headline.introduction,
            fontSize = TextUnit(16f, TextUnitType.Sp),
            fontStyle = FontStyle.Italic,
            modifier = Modifier.padding(16.dp, 16.dp, 16.dp)
        )
    }
}