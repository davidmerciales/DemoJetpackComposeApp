package com.example.androidexamtest.data.remote.api

import com.example.androidexamtest.data.remote.model.request.LoginUserRequest
import com.example.androidexamtest.data.remote.model.response.BaseResponse
import com.example.androidexamtest.data.remote.model.response.LoginUserResponse

interface ApiService {
    suspend fun login(loginUserRequest: LoginUserRequest): BaseResponse<LoginUserResponse>
}