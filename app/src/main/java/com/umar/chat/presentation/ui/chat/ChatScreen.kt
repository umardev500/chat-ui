package com.umar.chat.presentation.ui.chat

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.getScreenModel
import com.umar.chat.data.remote.mapper.withNextPrevFlags
import com.umar.chat.presentation.navigation.ChatNavArg
import com.umar.chat.presentation.ui.chat.components.ChatAppBar
import com.umar.chat.presentation.ui.chat.components.ChatInput
import com.umar.chat.presentation.ui.chat.components.ChatList
import javax.inject.Inject

class ChatScreen @Inject constructor(
    val param: ChatNavArg
) : Screen {
    @Composable
    override fun Content() {
        val screenModel = getScreenModel<ChatViewModel>()
        val messages = screenModel.messages.collectAsState()
        val thread = screenModel.createdThread.collectAsState()
        val loading = screenModel.loading.collectAsState()

        Scaffold(
            topBar = {
                ChatAppBar(
                    name = param.name,
                    photoUrl = param.photoUrl,
                    isLoading = loading.value
                )
            }
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.background)
                    .padding(top = paddingValues.calculateTopPadding())
                    .imePadding()
                    .navigationBarsPadding()
            ) {
                ChatList(
                    thread = thread.value,
                    messages = messages.value.withNextPrevFlags("1"),
                    modifier = Modifier
                        .weight(1f),
                    isLoading = loading.value
                )
                ChatInput { text ->
                    screenModel.sendMessage(text)
                }
            }
        }
    }
}