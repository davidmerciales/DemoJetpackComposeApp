package com.example.androidexamtest.data.remote.api

import com.example.androidexamtest.data.remote.model.request.LoginUserRequest
import com.example.androidexamtest.data.remote.model.response.BaseResponse
import com.example.androidexamtest.data.remote.model.response.LoginUserResponse
import com.example.androidexamtest.data.utils.Constants.BASE_URL
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import javax.inject.Inject

class ApiServiceImpl @Inject constructor(
    private val httpClient: HttpClient
): ApiService {
    override suspend fun login(loginUserRequest: LoginUserRequest): BaseResponse<LoginUserResponse> {
        val response = httpClient.post("$BASE_URL/login"){
            setBody(loginUserRequest)
        }
        return response.body()
    }
}