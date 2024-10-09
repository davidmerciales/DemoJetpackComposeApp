package com.example.androidexamtest.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class GetRoleRequest(
    val id: String,
    val accessToken: String
)
