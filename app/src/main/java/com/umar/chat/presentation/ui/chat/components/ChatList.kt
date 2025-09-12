package com.umar.chat.presentation.ui.chat.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.umar.chat.domain.model.Message
import com.umar.chat.domain.model.Thread

@Composable
fun ChatList(
    modifier: Modifier = Modifier,
    thread: Thread? = null,
    messages: List<Message>,
    isLoading: Boolean = true
) {

    LazyColumn(
        modifier = modifier,
        reverseLayout = true,
        contentPadding = PaddingValues(
            bottom = 32.dp
        )
    ) {
        items(messages, { it.id }) { item ->
            ChatBubble(item, isLoading)
        }
    }

}