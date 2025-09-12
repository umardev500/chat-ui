package com.umar.chat.presentation.ui.thread.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.eygraber.compose.placeholder.PlaceholderHighlight
import com.eygraber.compose.placeholder.fade
import com.eygraber.compose.placeholder.material3.placeholder
import com.umar.chat.R
import com.umar.chat.domain.model.Thread
import com.umar.chat.presentation.common.components.Avatar
import com.umar.chat.utils.clickWithFeedBack
import com.umar.chat.utils.toClockTimeString

@Composable
fun ThreadItem(
    thread: Thread,
    onClick: (Thread) -> Unit,
    isLoading: Boolean = false,
) {
    val colors = MaterialTheme.colorScheme
    val lastMessage = thread.lastMessage
    val lastMessageIsMine: Boolean = lastMessage?.isMine ?: false
    val mineIsRead: Boolean = lastMessage?.readAt != null && lastMessageIsMine
    val isUnread: Boolean = !lastMessageIsMine && thread.unreadCount!! > 0

    val checkIcon = remember(Unit) {
        if (mineIsRead) R.drawable.ms_done_all else R.drawable.ms_check
    }

    val placeholderModifier = Modifier
        .then(
            if (isLoading) Modifier.placeholder(
                visible = true,
                highlight = PlaceholderHighlight.fade(
                    highlightColor = colors.surface,
                )
            ) else Modifier
        )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickWithFeedBack {
                onClick(thread)
            }
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Avatar(
            image = thread.image,
            contentDescription = "Thread image",
            modifier = Modifier
                .then(
                    if (isLoading) Modifier.placeholder(
                        visible = true,
                        shape = CircleShape,
                        highlight = PlaceholderHighlight.fade(
                            highlightColor = colors.surface,
                        )
                    ) else Modifier
                )
            ,
            size = 56.dp,
        )
        Spacer(modifier = Modifier.width(16.dp))

        Column {
            // Thread title and time
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = thread.title,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Medium,
                    color = colors.onBackground,
                    modifier = placeholderModifier,
                    letterSpacing = 0.1.sp
                )
                Text(
                    text = lastMessage?.timestamp?.toClockTimeString()!!,
                    fontSize = 12.sp,
                    color = colors.secondary,
                    modifier = placeholderModifier,
                    letterSpacing = 0.1.sp
                )
            }

            Spacer(modifier = Modifier.height(2.dp))

            // Message content and unread count
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                if (lastMessageIsMine && !isLoading) {
                    Icon(
                        painter = painterResource(checkIcon),
                        contentDescription = "Check",
                        tint = if (lastMessage.readAt != null) colors.primary else colors.secondary,
                        modifier = Modifier
                            .size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(2.dp))
                }

                Text(
                    text = lastMessage?.content ?: "",
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontSize = 14.sp,
                    color = colors.onSurface,
                    fontWeight = if (isUnread) FontWeight.Medium else FontWeight.Normal,
                    modifier = placeholderModifier
                        .weight(1f),
                    letterSpacing = 0.1.sp
                )

                if (thread.unreadCount!! > 0 && !isLoading) {
                    Spacer(modifier = Modifier.width(8.dp))
                    Box(
                        modifier = Modifier
                            .size(22.dp)
                            .background(MaterialTheme.colorScheme.primary, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = thread.unreadCount.toString(),
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Medium,
                            color = MaterialTheme.colorScheme.onPrimary,
                        )
                    }

                }
            }
        }
    }
}