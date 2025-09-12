package com.umar.chat.data.remote.api

interface AuthApiService {
    suspend fun login(idToken: String): ApiResponse<LoginResponse>
}