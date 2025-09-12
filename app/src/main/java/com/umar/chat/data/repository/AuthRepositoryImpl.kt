package com.umar.chat.data.repository

import com.umar.chat.data.remote.api.ApiResponse
import com.umar.chat.data.remote.api.AuthApiService
import com.umar.chat.data.remote.api.LoginResponse
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val api: AuthApiService
) : AuthRepository {
    override suspend fun login(idToken: String): ApiResponse<LoginResponse> {
        return api.login(idToken)
    }
}