package com.umar.chat.domain.model

data class Thread(
    val id: Long,
    val title: String,
    val image: String? = null,
    val lastMessage: Message? = null,
    val unreadCount: Int? = 0
)
