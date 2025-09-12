package com.umar.chat.data.remote.api

import com.umar.chat.presentation.ui.thread.data.remote.api.ThreadApiModel

interface ThreadApiService {
    suspend fun getThreads(): List<ThreadApiModel>
}