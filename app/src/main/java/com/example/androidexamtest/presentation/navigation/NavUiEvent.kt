package com.example.androidexamtest.presentation.navigation

sealed class NavUiEvent {
    data class PopBackStack (val route: String? = null) : NavUiEvent()
    data class Navigate(val destinations: Destinations) : NavUiEvent()
    data class ShowDialog(val message: String) : NavUiEvent()
    data class LoadingDialog(val showLoading : Boolean = false, val isLoadingDim: Boolean = true) : NavUiEvent()
}