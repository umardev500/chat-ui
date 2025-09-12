package com.umar.chat.domain.repository

import com.umar.chat.domain.model.Thread

interface ThreadRepository {
    suspend fun fetchThreads(userId: Long): List<Thread>
}