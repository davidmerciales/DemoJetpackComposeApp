package com.example.androidexamtest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.androidexamtest.presentation.navigation.AppController
import com.example.androidexamtest.presentation.navigation.CollectEvents
import com.example.androidexamtest.presentation.navigation.Destinations
import com.example.androidexamtest.presentation.theme.AndroidExamTestTheme
import com.example.androidexamtest.presentation.ui.screens.ejo_esr_approval.EJOESRApprovalScreen
import com.example.androidexamtest.presentation.ui.screens.ejo_management.EJOManagementScreen
import com.example.androidexamtest.presentation.ui.screens.esr_management.ESRManagementScreen
import com.example.androidexamtest.presentation.ui.screens.fris_management.FRISManagementScreen
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

val LocalLoadingSession = compositionLocalOf { mutableStateOf(false) }

@Composable
fun Main(
    appController: AppController
) {
    val navController = rememberNavController()
    val showLoading = remember { mutableStateOf(false) }

    appController.CollectEvents(navController)

    CompositionLocalProvider(
        LocalLoadingSession provides showLoading
    ) {
        AndroidExamTestTheme {
            NavHost(
                navController = navController,
                startDestination = Destinations.HomeScreen
            ) {
                composable<Destinations.HomeScreen> {
                    HomeScreen()
                }
                composable<Destinations.EJOManagementScreen> {
                    EJOManagementScreen()
                }
                composable<Destinations.ESRManagementScreen> {
                    ESRManagementScreen()
                }
                composable<Destinations.FRISManagementScreen> {
                    FRISManagementScreen()
                }
                composable<Destinations.EJOESRApprovalScreen> {
                    EJOESRApprovalScreen()
                }
            }

            CircularLoading()
        }

    }

}

@Composable
fun CircularLoading(){
    if (!LocalLoadingSession.current.value) return
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.Black.copy(alpha = 0.2f))){
        CircularProgressIndicator(
            modifier = Modifier
                .width(64.dp)
                .align(Alignment.Center)
                .zIndex(1f),
            color = Color.Blue,
            trackColor = Color.Gray
        )
    }
}
