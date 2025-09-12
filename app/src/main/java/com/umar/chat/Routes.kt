package com.umar.chat

import cafe.adriel.voyager.core.screen.Screen
import com.umar.chat.presentation.navigation.ChatNavArg
import com.umar.chat.presentation.ui.auth.LoginScreen
import com.umar.chat.presentation.ui.chat.ChatScreen
import com.umar.chat.presentation.ui.thread.ThreadScreen

object Routes {
    fun thread(): Screen = ThreadScreen()
    fun chat(param: ChatNavArg): Screen = ChatScreen(param)
    fun login(): Screen = LoginScreen()
}