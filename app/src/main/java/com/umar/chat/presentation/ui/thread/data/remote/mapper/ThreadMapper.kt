package com.umar.chat.presentation.ui.thread.data.remote.mapper

import com.umar.chat.data.remote.mapper.toDomain
import com.umar.chat.domain.model.Thread
import com.umar.chat.presentation.ui.thread.data.remote.api.ThreadApiModel

fun ThreadApiModel.toDomain(userId: Long) = Thread(
    id = id,
    title = title,
    image = image,
    lastMessage = lastMessage?.toDomain(userId),
    unreadCount = unreadCount
)