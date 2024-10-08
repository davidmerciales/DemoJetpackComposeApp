package com.example.androidexamtest.data.remote.model.response

import kotlinx.serialization.Serializable

@Serializable
data class BaseResponse<T>(
    val status: String,
    val user: T
)