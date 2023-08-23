package com.brunoterra.contraster2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.brunoterra.contraster2.presentation.screens.Routes
import com.brunoterra.contraster2.presentation.screens.contrast.ContrastScreen
import com.brunoterra.contraster2.presentation.screens.samples.ArticleScreen
import com.brunoterra.contraster2.ui.theme.Contraster2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            Routes.ContrastScreen
            val navController = rememberNavController()

            Contraster2Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
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
                                entry.arguments?.getInt(Routes.ArticleScreen.ARG_BACKGROUND_COLOR)
                                    ?: -1,
                                entry.arguments?.getInt(Routes.ArticleScreen.ARG_FOREGROUND_COLOR)
                                    ?: -1
                            )
                        }
                    }
                }
            }
        }
    }
}