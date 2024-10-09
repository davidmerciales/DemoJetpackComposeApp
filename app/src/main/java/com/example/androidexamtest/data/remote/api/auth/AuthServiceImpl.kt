package com.example.androidexamtest.data.remote.api.auth

import com.example.androidexamtest.data.remote.model.request.LoginUserRequest
import com.example.androidexamtest.data.remote.model.response.auth.LoginResponse
import com.example.androidexamtest.data.remote.model.response.auth.LoginUserResponse
import com.example.androidexamtest.data.utils.Constants.BASE_URL
import com.example.androidexamtest.data.utils.Constants.LOGIN_TOKEN_STG
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.HttpHeaders
import javax.inject.Inject

class AuthServiceImpl @Inject constructor(
    private val httpClient: HttpClient
): AuthService {
    override suspend fun login(loginUserRequest: LoginUserRequest): LoginResponse<LoginUserResponse> {
        val response =  httpClient.post("$BASE_URL/login"){
            header(HttpHeaders.Authorization, "Bearer $LOGIN_TOKEN_STG")
            setBody(loginUserRequest)
        }
        return response.body()
    }
}