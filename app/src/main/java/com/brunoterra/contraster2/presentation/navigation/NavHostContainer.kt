package com.brunoterra.contraster2.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.brunoterra.contraster2.presentation.screens.Routes
import com.brunoterra.contraster2.presentation.screens.contrast.ContrastScreen
import com.brunoterra.contraster2.presentation.screens.samples.ArticleScreen

@Composable
fun NavHostContainer(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Routes.ContrastScreen.route
    ) {
        composable(Routes.ContrastScreen.route) {
            ContrastScreen { bgc, fgc ->
                navController.navigate(Routes.ArticleScreen.withArgs(bgc, fgc))
            }
        }

        composable(
            route = Routes.ArticleScreen.route,
            arguments = listOf(
                navArgument(Routes.ArticleScreen.ARG_BACKGROUND_COLOR) {
                    type = NavType.IntType
                },
                navArgument(Routes.ArticleScreen.ARG_FOREGROUND_COLOR) {
                    type = NavType.IntType
                },
            )
        ) { entry ->
            ArticleScreen(
                entry.getArgOrDefault(Routes.ArticleScreen.ARG_BACKGROUND_COLOR, -1),
                entry.getArgOrDefault(Routes.ArticleScreen.ARG_FOREGROUND_COLOR, -1)
            )
        }
    }
}

fun <T> NavBackStackEntry.getArgOrDefault(key: String, default: T): T {
    return arguments?.get(key) as? T ?: default
}