package com.umar.chat.data.remote.mapper

import com.umar.chat.data.remote.api.UserApiModel
import com.umar.chat.domain.model.User

fun UserApiModel.toDomain() = User(
    id = id,
    email = email,
    name = name,
    avatarUrl = avatarUrl,
)
