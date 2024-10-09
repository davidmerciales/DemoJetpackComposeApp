package com.example.androidexamtest.presentation.ui.screens.login

import androidx.lifecycle.viewModelScope
import com.example.androidexamtest.LocalLoadingSession
import com.example.androidexamtest.data.remote.model.request.LoginUserRequest
import com.example.androidexamtest.domain.model.GetRoleRequest
import com.example.androidexamtest.domain.usecase.GetRoleUseCase
import com.example.androidexamtest.domain.usecase.LoginUseCase
import com.example.androidexamtest.domain.usecase.RolePermissionUtils
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
    private val loginUseCase: LoginUseCase,
    private val getRoleUseCase: GetRoleUseCase
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

        loginUseCase(
            params = LoginUserRequest(
                email = state.username,
                password = state.password
            ),
            onSuccess = {
                fetchRole(it.roleId, it.accessToken)
            },
            onFailure = {
                navController.sendUiEvent(NavUiEvent.ShowDialog("Login Failed!"))
            }
        )

    }

    private fun fetchRole(id: String, accessToken: String) = viewModelScope.launch{
        getRoleUseCase(
            params = GetRoleRequest(
                id = id,
                accessToken = accessToken
            ),
            onSuccess = {
                val permission = checkRolePermissions(it.permissions.toList())

                when (permission) {
                    RolePermissionUtils.EJO_MANAGEMENT.permission -> {
                        navController.sendUiEvent(NavUiEvent.Navigate(Destinations.EJOManagementScreen))
                    }
                    RolePermissionUtils.EJO_ESR_APPROVAL.permission -> {
                        navController.sendUiEvent(NavUiEvent.Navigate(Destinations.EJOESRApprovalScreen))
                    }
                    RolePermissionUtils.ESR_MANAGEMENT.permission -> {
                        navController.sendUiEvent(NavUiEvent.Navigate(Destinations.ESRManagementScreen))
                    }
                    RolePermissionUtils.FRIS_MANAGEMENT.permission -> {
                        navController.sendUiEvent(NavUiEvent.Navigate(Destinations.FRISManagementScreen))
                    }
                    else -> {

                    }
                }
            },
            onFailure = {
                navController.sendUiEvent(NavUiEvent.ShowDialog("Failed!"))
            }
        )

        state.isLoading = false
    }

    private fun checkRolePermissions(permissions: List<String>): String {
        val isEjoManagement = RolePermissionUtils.EJO_MANAGEMENT.permission in permissions.map { it }
        val isEjoEsrApproval = RolePermissionUtils.EJO_ESR_APPROVAL.permission in permissions.map { it }
        val isEsrManagement = RolePermissionUtils.ESR_MANAGEMENT.permission in permissions.map { it }
        val isFrisManagement = RolePermissionUtils.FRIS_MANAGEMENT.permission in permissions.map { it }

        when {
            isEjoManagement -> {
                return RolePermissionUtils.EJO_MANAGEMENT.permission
            }
            isEjoEsrApproval -> {
                return RolePermissionUtils.EJO_ESR_APPROVAL.permission
            }
            isEsrManagement -> {
                return RolePermissionUtils.ESR_MANAGEMENT.permission
            }
            isFrisManagement -> {
                return RolePermissionUtils.FRIS_MANAGEMENT.permission
            }
            else -> {
                return ""
            }
        }
    }

    private fun forgotPassword() = viewModelScope.launch{
        navController.sendUiEvent(NavUiEvent.ShowDialog("Please remember your password!"))
    }
}