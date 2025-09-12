package com.umar.chat.presentation.navigation

data class ChatNavArg(
    val type: ChatType,
    val threadId: String? = null,     // nullable if type is USER (no chat yet)
    val userId: String? = null,     // only set if type is USER
    val name: String,               // name of the user or group
    val photoUrl: String? = null    // profile or group photo
)

enum class ChatType {
    USER,
    GROUP,
}