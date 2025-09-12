package com.umar.chat.presentation.ui.thread.data.mock

import com.umar.chat.domain.model.Message
import com.umar.chat.domain.model.Thread
import com.umar.chat.domain.model.User
import net.datafaker.Faker
import java.time.Instant
import kotlin.random.Random

val faker = Faker()

fun generateMockThreads(
    threadCount: Int,
    userId: Long,
): List<Thread> {
    return (1..threadCount).map { tIndex ->
        val isMine = Random.nextBoolean()
        val sender = if (isMine) {
            User(
                id = userId,
                email = faker.internet().emailAddress(),
                name = faker.name().fullName(),
                avatarUrl = "https://api.dicebear.com/9.x/big-smile/png?seed=${
                    faker.number().numberBetween(1, 100)
                }"
            )
        } else {
            User(
                id = faker.number().numberBetween(1L, 100L),
                email = faker.internet().emailAddress(),
                name = faker.name().fullName(),
                avatarUrl = "https://api.dicebear.com/9.x/big-smile/png?seed=${
                    faker.number().numberBetween(1, 100)
                }"
            )
        }

        val lastMessage = Message(
            id = tIndex.toLong(),
            threadId = tIndex.toLong(),
            sender = sender,
            content = faker.lorem().sentence(),
            timestamp = Instant.now().minusSeconds(Random.nextLong(60, 3600)),
            readAt = if (!isMine && Random.nextBoolean()) Instant.now() else null,
            isMine = isMine
        )

        Thread(
            id = tIndex.toLong(),
            title = faker.team().name(),
            image = "https://api.dicebear.com/9.x/big-smile/png?seed=${
                faker.number().numberBetween(1, 100)
            }",
            lastMessage = lastMessage,
            unreadCount = if (!isMine && Random.nextBoolean()) Random.nextInt(1, 10) else 0
        )
    }
}

val mockThreads = generateMockThreads(25, 1L)