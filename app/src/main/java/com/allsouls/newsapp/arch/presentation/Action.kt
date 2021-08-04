package com.allsouls.newsapp.arch.presentation

data class Action(
    val type: String,
    val payload: Any? = null
)