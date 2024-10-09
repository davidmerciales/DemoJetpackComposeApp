package com.example.androidexamtest.domain.usecase

import com.example.androidexamtest.data.remote.api.role.RoleApiService
import com.example.androidexamtest.data.remote.model.response.role.RoleResponse
import com.example.androidexamtest.domain.model.GetRoleRequest
import com.example.androidexamtest.domain.utils.Either
import com.example.androidexamtest.domain.utils.Failure
import com.example.androidexamtest.domain.utils.Success
import javax.inject.Inject

class GetRoleUseCase @Inject constructor(
    private val roleApiService: RoleApiService
) : UseCase<RoleResponse, GetRoleRequest>() {
    override suspend fun run(getRoleRequest: GetRoleRequest): Either<Exception, RoleResponse> {
        return try {
            val response = roleApiService.getRole(getRoleRequest)
            if (response.status == "success") {
                Success(response.data)
            } else {
                Failure(Exception("Login failed with status: ${response.status}"))
            }
        }catch (e: Exception){
            Failure(e)
        }
    }
}

enum class RolePermissionUtils(val permission: String) {
    EJO_MANAGEMENT("EJO::MANAGEMENT"),
    EJO_ESR_APPROVAL("EJO::ESR::APPROVAL"),
    ESR_MANAGEMENT("ESR::MANAGEMENT"),
    FRIS_MANAGEMENT("FRIS::MANAGEMENT")
}