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
        var username: String
        var password: String
    }

    class MutableLoginState: LoginState {
        override var username: String by mutableStateOf("")
        override var password: String by mutableStateOf("")
    }
}