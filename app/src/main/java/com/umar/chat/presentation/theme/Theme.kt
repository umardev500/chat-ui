package com.umar.chat.presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80
)

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF6B56F4),
    onPrimary = Colors.White,
    secondary = Colors.Gray._500,
    onSecondary = Colors.White,
    background = Colors.White,
    onBackground = Colors.Gray._900,
    surface = Colors.Gray._200,
    surfaceVariant = Colors.Gray._100,
    onSurface = Colors.Gray._900,
    error = Colors.Red._600,
    onError = Colors.White
)

@Composable
fun ChatTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = LightColorScheme,
        typography = Typography,
        content = content
    )
}