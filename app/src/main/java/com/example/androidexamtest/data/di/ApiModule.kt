package com.example.androidexamtest.data.di

import android.util.Log
import com.example.androidexamtest.data.remote.api.auth.AuthService
import com.example.androidexamtest.data.remote.api.auth.AuthServiceImpl
import com.example.androidexamtest.data.remote.api.role.RoleApiService
import com.example.androidexamtest.data.remote.api.role.RoleApiServiceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpResponseValidator
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.request
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Singleton
    @Provides
    fun provideHttpClient(): HttpClient {
        return HttpClient(Android) {
            install(Logging) {
                logger = object : Logger {
                    override fun log(message: String) {
                        Log.d("Http", message)
                    }
                }
                level = LogLevel.ALL
            }
            install(ContentNegotiation) {
                json(Json {
                    explicitNulls = false
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                })
            }
            install(DefaultRequest) {
                header(HttpHeaders.ContentType, ContentType.Application.Json)
            }
            HttpResponseValidator {
                validateResponse { response: HttpResponse ->
                    Log.d("android-request", "request: ${response.request.url}")
                    val statusCode = response.status.value
                    Log.d("Http status", "HTTP status: $statusCode")
                }
            }
        }
    }

    suspend fun getWithAuthorization(
        httpClient: HttpClient,
        url: String,
        tokenProvider: () -> String
    ): HttpResponse {
        return httpClient.get(url) {
            header(HttpHeaders.Authorization, "Bearer ${tokenProvider()}")
        }
    }


    @Singleton
    @Provides
    fun provideApiService(httpClient: HttpClient): AuthService =
        AuthServiceImpl(httpClient)

    @Singleton
    @Provides
    fun provideRoleApiService(httpClient: HttpClient): RoleApiService =
        RoleApiServiceImpl(httpClient)
}