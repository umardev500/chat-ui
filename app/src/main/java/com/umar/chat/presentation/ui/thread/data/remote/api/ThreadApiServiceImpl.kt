package com.umar.chat.presentation.ui.thread.data.remote.api

import com.umar.chat.BuildConfig
import com.umar.chat.data.remote.api.ThreadApiService
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.accept
import io.ktor.client.request.get
import io.ktor.client.request.url
import io.ktor.http.ContentType
import javax.inject.Inject

class ThreadApiServiceImpl @Inject constructor(
    private val client: HttpClient
) : ThreadApiService {
    override suspend fun getThreads(): List<ThreadApiModel> {
        return client.get {
            url("${BuildConfig.API_URL}/threads")
            accept(ContentType.Application.Json)
        }.body()
    }
}