package com.example.androidexamtest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.androidexamtest.presentation.navigation.AppController
import com.example.androidexamtest.presentation.navigation.CollectEvents
import com.example.androidexamtest.presentation.navigation.Destinations
import com.example.androidexamtest.presentation.theme.AndroidExamTestTheme
import com.example.androidexamtest.presentation.ui.screens.detail.DetailScreen
import com.example.androidexamtest.presentation.ui.screens.login.HomeScreen
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var appController: AppController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Main(appController)
        }
    }
}

@Composable
fun Main(
    appController: AppController
) {
    val navController = rememberNavController()

    appController.CollectEvents(navController)

    AndroidExamTestTheme {
        NavHost(
            navController = navController,
            startDestination = Destinations.HomeScreen
        ) {
            composable<Destinations.HomeScreen> {
                HomeScreen()
            }
            composable<Destinations.DetailScreen> {
                DetailScreen()
            }
        }
    }
}
