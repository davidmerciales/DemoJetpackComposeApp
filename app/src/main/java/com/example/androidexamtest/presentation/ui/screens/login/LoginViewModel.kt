package com.example.androidexamtest.presentation.ui.screens.login

import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.androidexamtest.presentation.navigation.AppController
import com.example.androidexamtest.presentation.navigation.Destinations
import com.example.androidexamtest.presentation.navigation.NavUiEvent
import com.example.androidexamtest.presentation.ui.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val navController: AppController,
) : BaseViewModel<LoginContract.LoginState, LoginContract.LoginEvent>() {
    override val state: LoginContract.LoginState = LoginContract.MutableLoginState()

    init {
        viewModelScope.launch {
            event.collect { event ->
                when (event) {
                    LoginContract.LoginEvent.OnLoginButtonClicked -> login()
                    LoginContract.LoginEvent.OnForgotPasswordClicked -> forgotPassword()
                }
            }
        }
    }

    private fun login() = viewModelScope.launch{
        if (state.username != "admin" && state.password != "admin"){
            navController.sendUiEvent(NavUiEvent.ShowDialog("Incorrect username or password"))
            return@launch
        }
        navController.sendUiEvent(NavUiEvent.Navigate(Destinations.DetailScreen))
        navController.sendUiEvent(NavUiEvent.ShowDialog("Login success!"))

    }

    private fun forgotPassword()= viewModelScope.launch{
        navController.sendUiEvent(NavUiEvent.ShowDialog("Please remember your password!"))
    }
}