package com.umar.chat.utils

import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

fun String.toClockTimeString(): String {
    val javaInstant = Instant.parse(this) // parse ISO 8601 string`
    val localDateTime = LocalDateTime.ofInstant(javaInstant, ZoneId.systemDefault())
    val formatter = DateTimeFormatter.ofPattern("hh:mm a")
    return localDateTime.format(formatter)
}

fun Instant.toClockTimeString(): String {
    val localDateTime = LocalDateTime.ofInstant(this, ZoneId.systemDefault())
    val formatter = DateTimeFormatter.ofPattern("hh:mm a")
    return localDateTime.format(formatter)
}
