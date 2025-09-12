package com.umar.chat.domain.model

data class Thread(
    val id: String,
    val title: String,
    val image: String? = null,
    val lastMessage: Message? = null,
    val unreadCount: Int? = 0
)
