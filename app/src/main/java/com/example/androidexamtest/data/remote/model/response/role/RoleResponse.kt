package com.example.androidexamtest.data.remote.model.response.role

import kotlinx.serialization.Serializable

@Serializable
data class RoleResponse(
    val id: String,
    val name: String,
    val alias: String,
    val description: String,
    val isActive: Boolean,
    val isDefault: Boolean,
    val permissions: Array<String>,
    val docType: String,
    val isDeleted: Boolean,
    val dateCreated: String,
    val dateUpdated: String
)
