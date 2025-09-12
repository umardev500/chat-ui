package com.umar.chat.domain.model

import java.time.Instant

data class Message(
    val id: Long,
    val threadId: Long,
    val sender: User,
    val content: String,
    val timestamp: Instant,
    val readAt: Instant?,
    val isMine: Boolean = false,
    val prevIsSame: Boolean = false,
    val nextIsSame: Boolean = false,
)
