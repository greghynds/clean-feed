package com.allsouls.newsapp.feed.ui

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.Date

@SuppressLint("SimpleDateFormat")
private val formatter = SimpleDateFormat("d MMMM, yyyy")

fun formatDate(date: Date): String {
    return formatter.format(date)
}