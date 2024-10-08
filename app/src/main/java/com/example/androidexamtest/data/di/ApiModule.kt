package com.example.androidexamtest.data.di

import android.util.Log
import com.example.androidexamtest.data.remote.api.ApiService
import com.example.androidexamtest.data.remote.api.ApiServiceImpl
import com.example.androidexamtest.data.utils.Constants.LOGIN_TOKEN_STG
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
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.request
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.append
import io.ktor.serialization.kotlinx.json.json
import io.ktor.util.StringValues
import kotlinx.serialization.json.Json
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {
    @Singleton
    @Provides
    fun provideHttpClient() : HttpClient {
        val stringValue = StringValues.build(caseInsensitiveName = true) {
            append(HttpHeaders.ContentType, ContentType.Application.Json)
            append(HttpHeaders.Authorization, "Bearer $LOGIN_TOKEN_STG")
        }
        return HttpClient(Android){
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
                headers.appendAll(stringValue)
            }
            HttpResponseValidator {
                validateResponse { response: HttpResponse ->
                    Log.d("android-request","request: ${response.request.url}")
                    val statusCode = response.status.value
                    Log.d("Http status", "HTTP status: $statusCode")
                }
            }
        }
    }

    @Singleton
    @Provides
    fun provideApiService(httpClient: HttpClient): ApiService = ApiServiceImpl(httpClient)
}