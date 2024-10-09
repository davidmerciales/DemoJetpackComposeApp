package com.example.androidexamtest.presentation.ui.screens.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.androidexamtest.presentation.ui.UiEvent
import com.example.androidexamtest.presentation.ui.UiState

class LoginContract {

    sealed interface LoginEvent : UiEvent {
        data object OnLoginButtonClicked: LoginEvent
        data object OnForgotPasswordClicked: LoginEvent
    }

    interface LoginState: UiState {
        var email: String
        var password: String
        var isLoading: Boolean
    }

    class MutableLoginState: LoginState {
        override var email: String by mutableStateOf("")
        override var password: String by mutableStateOf("")
        override var isLoading: Boolean by mutableStateOf(false)
    }
}