package com.example.androidexamtest.data.remote.api.role

import com.example.androidexamtest.data.remote.model.response.BaseResponse
import com.example.androidexamtest.data.remote.model.response.role.RoleResponse
import com.example.androidexamtest.domain.model.GetRoleRequest

interface RoleApiService {
    suspend fun getRole(getRoleRequest: GetRoleRequest): BaseResponse<RoleResponse>
}