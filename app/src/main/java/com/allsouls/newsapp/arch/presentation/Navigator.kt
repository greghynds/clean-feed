package com.allsouls.newsapp.arch.presentation

import com.gwh.routes.Route

interface Navigator {
    fun navigateTo(route: Route)
}