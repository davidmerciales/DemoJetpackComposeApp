package com.example.androidexamtest.data.remote.api.auth

import com.example.androidexamtest.data.remote.model.request.LoginUserRequest
import com.example.androidexamtest.data.remote.model.response.auth.LoginResponse
import com.example.androidexamtest.data.remote.model.response.auth.LoginUserResponse

interface AuthService {
    suspend fun login(loginUserRequest: LoginUserRequest): LoginResponse<LoginUserResponse>
}