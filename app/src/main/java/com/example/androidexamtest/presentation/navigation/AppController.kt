package com.example.androidexamtest.presentation.navigation

import android.widget.Toast
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject

@ActivityRetainedScoped
class AppController @Inject constructor() {
    private val _navEvent = MutableSharedFlow<NavUiEvent>(0)
    val navEvent = _navEvent.asSharedFlow()
    suspend fun sendUiEvent(event: NavUiEvent) {
        _navEvent.emit(event)
    }
}

@Composable
fun AppController.CollectEvents(
    navController: NavController,
) {
    val context = LocalContext.current
    var isLoading by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = Unit) {
        navEvent.collect { appEvent ->
            when (appEvent) {
                is NavUiEvent.Navigate -> {
                    navController.navigate(appEvent.destinations)
                }

                is NavUiEvent.PopBackStack -> {
                    navController.popBackStack()
                }

                is NavUiEvent.ShowDialog -> {
                    Toast.makeText(context, appEvent.message, Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}


