package com.umar.chat.data.remote.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MessageApiModel(
    @SerialName("id") val id: Long,
    @SerialName("thread_id") val threadId: Long,
    @SerialName("sender") val sender: UserApiModel,
    @SerialName("content") val content: String,
    @SerialName("timestamp") val timestamp: String,      // ISO-8601 string
    @SerialName("read_at") val readAt: String? = null   // optional ISO-8601 string
)
