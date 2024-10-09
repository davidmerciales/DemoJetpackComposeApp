package com.example.androidexamtest.data.remote.model.response.auth

import kotlinx.serialization.Serializable

@Serializable
data class LoginResponse<T>(
    val status: String,
    val user: T
)