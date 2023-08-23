package com.brunoterra.contraster2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.brunoterra.contraster2.presentation.navigation.NavHostContainer
import com.brunoterra.contraster2.presentation.screens.Routes
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
                    NavHostContainer(navController = navController)
                }
            }
        }
    }
}