package com.umar.chat.data.mock

import com.umar.chat.domain.model.User
import net.datafaker.Faker

fun generateMockUsers(userCount: Int = 25): List<User> {
    val faker = Faker()

    return (1..userCount).map { uIndex ->
        User(
            id = uIndex.toLong(),
            email = faker.internet().emailAddress(),
            name = faker.name().fullName(),
            avatarUrl = "https://api.dicebear.com/9.x/big-smile/png?seed=${
                faker.name().firstName()
            }",
        )
    }
}

val mockUsers = generateMockUsers()