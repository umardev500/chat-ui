package com.umar.chat.presentation.common.components

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.umar.chat.data.mock.mockUsers
import com.umar.chat.domain.model.User

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UsersBottomSheet(
    modifier: Modifier = Modifier,
    sheetState: SheetState,
    onUserClick: (User) -> Unit = {},
    onDismissRequest: () -> Unit,
) {
    val colors = MaterialTheme.colorScheme

    ModalBottomSheet(
        sheetState = sheetState,
        onDismissRequest = onDismissRequest,
        containerColor = colors.background,
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
        ) {
            Row(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
            ) {
                SearchInput(
                    modifier = Modifier.fillMaxWidth(),
                    onDebounceDone = { text ->
                        Log.d("debounce", text)
                    }
                )
            }

            Spacer(modifier = Modifier.height(26.dp))

            UserList(
                users = mockUsers,
                modifier = Modifier
                    .weight(1f),
                onItemClick = onUserClick
            )
        }
    }
}