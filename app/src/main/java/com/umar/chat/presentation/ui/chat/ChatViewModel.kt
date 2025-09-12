package com.umar.chat.presentation.ui.chat

import android.util.Log
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.umar.chat.domain.model.Message
import com.umar.chat.domain.model.Thread
import com.umar.chat.presentation.ui.chat.data.mock.mockMessages
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class ChatViewModel @Inject constructor() : ScreenModel {
    private val _loading = MutableStateFlow<Boolean>(true)
    val loading = _loading.asStateFlow()

    private val _messages = MutableStateFlow<List<Message>>(emptyList())
    val messages = _messages.asStateFlow()

    private val _createdThread = MutableStateFlow<Thread?>(null)
    val createdThread = _createdThread.asStateFlow()

    init {
        _messages.value = mockMessages
        screenModelScope.launch {
            delay(1000)
            _loading.value = false
        }
    }

    fun sendMessage(text: String) {
        // TODO: send message to server
        // if message is initialize message then create thread
        // else send message to thread
        Log.d("chatting", text)
    }
}