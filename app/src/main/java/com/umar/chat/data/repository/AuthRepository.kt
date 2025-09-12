package com.umar.chat.data.repository

import com.umar.chat.data.remote.api.ApiResponse
import com.umar.chat.data.remote.api.LoginResponse

interface AuthRepository {
    suspend fun login(idToken: String) : ApiResponse<LoginResponse>
}