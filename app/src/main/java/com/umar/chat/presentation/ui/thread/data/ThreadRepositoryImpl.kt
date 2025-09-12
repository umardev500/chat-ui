package com.umar.chat.presentation.ui.thread.data

import com.umar.chat.data.remote.api.ThreadApiService
import com.umar.chat.domain.model.Thread
import com.umar.chat.domain.repository.ThreadRepository
import com.umar.chat.presentation.ui.thread.data.remote.api.ThreadApiModel
import com.umar.chat.presentation.ui.thread.data.remote.mapper.toDomain
import javax.inject.Inject

class ThreadRepositoryImpl @Inject constructor(
    private val api: ThreadApiService
) : ThreadRepository {
    override suspend fun fetchThreads(userId: Long): List<Thread> {
        val apiThreads: List<ThreadApiModel> = api.getThreads()
        return apiThreads.map { it.toDomain(userId) }
    }
}
