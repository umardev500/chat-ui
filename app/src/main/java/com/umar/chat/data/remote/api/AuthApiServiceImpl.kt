package com.umar.chat.data.remote.api

import com.umar.chat.BuildConfig
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.http.ContentType
import io.ktor.http.contentType
import javax.inject.Inject

class AuthApiServiceImpl @Inject constructor(
    private val client: HttpClient
) : AuthApiService {
    override suspend fun login(idToken: String): ApiResponse<LoginResponse> {
        return client.post {
            url("${BuildConfig.API_URL}/auth/login")
            contentType(ContentType.Application.Json)
            setBody(LoginRequest(idToken))
        }.body()
    }
}