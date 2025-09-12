package com.umar.chat.data.remote.mapper

import com.umar.chat.data.remote.api.MessageApiModel
import com.umar.chat.domain.model.Message
import java.time.Instant

fun MessageApiModel.toDomain(currentUserId: String) = Message(
    id = id,
    threadId = threadId,
    sender = sender.toDomain(),
    content = content,
    timestamp = Instant.parse(timestamp),
    readAt = readAt?.let { Instant.parse(it) },
    isMine = sender.id == currentUserId
)

fun List<Message>.withNextPrevFlags(currentUserId: String): List<Message> {
    return this.mapIndexed { index, message ->
        val prevIsSame = this.getOrNull(index + 1)?.sender?.id == message.sender.id
        val nextIsSame = this.getOrNull(index - 1)?.sender?.id == message.sender.id

        message.copy(
            prevIsSame = prevIsSame,
            nextIsSame = nextIsSame
        )
    }
}