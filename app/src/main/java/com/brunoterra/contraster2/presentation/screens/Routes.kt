package com.brunoterra.contraster2.presentation.screens

import android.util.Log

sealed class Routes(val route: String) {

    object ContrastScreen : Routes("ContrastScreen")
    object ArticleScreen : Routes("ArticleScreen/{background_color}/{foreground_color}"){
        const val ARG_BACKGROUND_COLOR = "background_color"
        const val ARG_FOREGROUND_COLOR = "foreground_color"
    }

    fun withArgs(vararg args: Any): String {
        return buildString {
            append(baseRoute())
            args.forEach {
                append("/$it")
            }
        }.also { Log.i("Porsche", "withArgs: $it") }
    }
}

fun Routes.baseRoute(): String {
    if (route.contains('/')) {
        return route.split('/')[0]
    }
    return route
}
