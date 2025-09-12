package com.umar.chat.presentation.ui.thread

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.umar.chat.domain.model.Thread
import com.umar.chat.presentation.ui.thread.data.mock.mockThreads
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class ThreadViewModel @Inject constructor() : ScreenModel {
    private val _threads = MutableStateFlow<List<Thread>>(emptyList())
    val threads = _threads.asStateFlow()

    init {
        screenModelScope.launch {
            _threads.value = mockThreads
        }
    }
}