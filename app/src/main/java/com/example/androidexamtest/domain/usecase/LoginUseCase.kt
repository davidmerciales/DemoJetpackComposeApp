package com.example.androidexamtest.domain.usecase

import com.example.androidexamtest.data.remote.api.auth.AuthService
import com.example.androidexamtest.data.remote.model.request.LoginUserRequest
import com.example.androidexamtest.data.remote.model.response.auth.LoginUserResponse
import com.example.androidexamtest.domain.utils.Either
import com.example.androidexamtest.domain.utils.Failure
import com.example.androidexamtest.domain.utils.Success
import javax.inject.Inject


class LoginUseCase @Inject constructor(
    private val authService: AuthService
): UseCase<LoginUserResponse, LoginUserRequest>(){
    override suspend fun run(params: LoginUserRequest): Either<Exception, LoginUserResponse> {
        return try {
            val response = authService.login(params)
            if (response.status == "success") {
                Success(response.user)
            } else {
                Failure(Exception("Login failed with status: ${response.status}"))
            }
        } catch (e: Exception) {
            Failure(e)
        }
    }
}