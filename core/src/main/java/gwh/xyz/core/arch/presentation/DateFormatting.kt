package gwh.xyz.core.arch.presentation

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

private val formatter = SimpleDateFormat("d MMMM, yyyy", Locale.ENGLISH)

fun formatDate(date: Date): String {
    return formatter.format(date)
}

fun dateFromTimestamp(timestamp: Long): Date {
    return Date().apply { time = timestamp * 1000 }
}