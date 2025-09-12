package com.umar.chat.presentation.ui.thread.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.umar.chat.domain.model.Thread

@Composable
fun ThreadList(
    threads: List<Thread>,
    onThreadClick: (Thread) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier) {
        items(threads, { it.id }) { item ->
            ThreadItem(thread = item, onClick = onThreadClick)
        }
    }
}