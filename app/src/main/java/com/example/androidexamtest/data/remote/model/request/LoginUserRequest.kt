package com.example.androidexamtest.data.remote.model.request

import kotlinx.serialization.Serializable

@Serializable
data class LoginUserRequest(
    val email: String,
    val password: String
)
