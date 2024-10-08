package com.example.androidexamtest.data.remote.model.response

import kotlinx.serialization.Serializable

@Serializable
data class LoginUserResponse(
    val id: String,
    val email: String,
    val firstName: String,
    val lastName: String,
    val roleId: String,
    val accessToken: String
)
