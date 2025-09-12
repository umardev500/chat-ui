package com.umar.chat.presentation.ui.chat.data.mock

import com.umar.chat.domain.model.Message
import com.umar.chat.domain.model.User
import com.umar.chat.presentation.ui.thread.data.mock.faker
import java.time.Instant
import kotlin.random.Random

fun generateMockChats(
    currentUserId: String,
    messageCount: Int = 25
): List<Message> {
    return (1..messageCount).map { mIndex ->
        val isMine = Random.nextBoolean()
        val sender = if (isMine) {
            User(
                id = currentUserId,
                email = faker.internet().emailAddress(),
                name = faker.name().fullName(),
                avatarUrl = "https://api.dicebear.com/9.x/big-smile/png?seed=Alex"
            )
        } else {
            User(
                id = "2",
                email = faker.internet().emailAddress(),
                name = faker.name().fullName(),
                avatarUrl = "https://api.dicebear.com/9.x/big-smile/png?seed=SteveJob"
            )
        }

        Message(
            id = "m$mIndex",
            threadId = "t$mIndex",
            sender = sender,
            content = faker.lorem().sentence(),
            timestamp = Instant.now().minusSeconds(Random.nextLong(60, 3600)),
            readAt = if (!isMine && Random.nextBoolean()) Instant.now() else null,
            isMine = isMine
        )
    }
}

val mockMessages = generateMockChats(currentUserId = "1")