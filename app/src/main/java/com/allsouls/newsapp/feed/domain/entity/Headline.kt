package com.allsouls.newsapp.feed.domain.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.Date

@Parcelize
data class Headline(
    val headline: String,
    val updated: Date,
    val introduction: String
) : Parcelable