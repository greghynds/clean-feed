package com.allsouls.newsapp.arch.presentation

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import xyz.gwh.redux.Action
import xyz.gwh.redux.Middleware
import xyz.gwh.redux.Store


fun <State> createThunk(
    type: String,
    dispatchers: Dispatchers,
    block: suspend (Store<State>) -> Action
): Middleware<State> = { store ->
    { action ->
        when {
            action.isOfType(type) ->
                CoroutineScope(dispatchers.main).launch {
                    val newAction = withContext(dispatchers.io) { block(store) }
                    store.dispatch(newAction)
                }
        }
        action
    }
}