package com.umar.chat.domain.model

data class User(
    val id: String,
    val email: String,
    val name: String,
    val avatarUrl: String? = null
)
