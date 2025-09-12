package com.umar.chat.presentation.ui.thread.data.remote.api

import com.umar.chat.data.remote.api.MessageApiModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ThreadApiModel(
    @SerialName("id") val id: Long,
    @SerialName("title") val title: String,
    @SerialName("image") val image: String? = null,
    @SerialName("last_message") val lastMessage: MessageApiModel? = null,
    @SerialName("unread_count") val unreadCount: Int = 0
)