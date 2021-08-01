package com.allsouls.newsapp.feed.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gwh.routes.Route

abstract class Model<State> : ViewModel() {
    abstract val state: MutableLiveData<State>
    abstract val routes: MutableLiveData<Route>

    fun updates(): LiveData<State> = state
    fun routes(): LiveData<Route> = routes
}