package com.umar.chat.presentation.ui.thread

import android.util.Log
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.umar.chat.domain.model.Thread
import com.umar.chat.domain.repository.ThreadRepository
import com.umar.chat.presentation.ui.thread.data.mock.mockThreads
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class ThreadViewModel @Inject constructor(
    private val repository: ThreadRepository
) : ScreenModel {
    private val _threads = MutableStateFlow<List<Thread>>(emptyList())
    val threads = _threads.asStateFlow()

    private val _isLoading = MutableStateFlow<Boolean>(true)
    val isLoading = _isLoading.asStateFlow()

    init {
        screenModelScope.launch {
            _threads.value = mockThreads
        }
    }

    fun loadThreads(userId: Long) {
        screenModelScope.launch {
            try {
                _threads.value = repository.fetchThreads(userId)
            } catch (e: Exception) {
                Log.d("API", "${e.message}")
                e.printStackTrace()
            } finally {
                _isLoading.value = false
            }
        }
    }
}