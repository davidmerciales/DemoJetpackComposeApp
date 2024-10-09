package com.example.androidexamtest.data.remote.api.role

import com.example.androidexamtest.data.di.ApiModule
import com.example.androidexamtest.data.remote.model.response.BaseResponse
import com.example.androidexamtest.data.remote.model.response.role.RoleResponse
import com.example.androidexamtest.data.utils.Constants.BASE_URL
import com.example.androidexamtest.domain.model.GetRoleRequest
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import javax.inject.Inject

class RoleApiServiceImpl @Inject constructor(
    private val httpClient: HttpClient
) : RoleApiService {

    override suspend fun getRole(getRoleRequest: GetRoleRequest): BaseResponse<RoleResponse> {
            val response = ApiModule.getWithAuthorization(
                httpClient,
                "$BASE_URL/roles/${getRoleRequest.id}"
            ) { getRoleRequest.accessToken }
        return response.body()
    }
}
