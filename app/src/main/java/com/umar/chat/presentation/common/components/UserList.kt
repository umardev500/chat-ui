package com.umar.chat.presentation.common.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.umar.chat.domain.model.User

@Composable
fun UserList(
    users: List<User>,
    modifier: Modifier = Modifier,
    onItemClick: (User) -> Unit = {}
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(
            bottom = 32.dp
        )
    ) {
        items(users, { it.id }) { item ->
            UserListItem(item, onClick = onItemClick)
        }
    }
}