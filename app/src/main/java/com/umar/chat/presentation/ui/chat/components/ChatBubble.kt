package com.umar.chat.presentation.ui.chat.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.eygraber.compose.placeholder.PlaceholderHighlight
import com.eygraber.compose.placeholder.fade
import com.eygraber.compose.placeholder.material3.placeholder
import com.umar.chat.domain.model.Message
import com.umar.chat.presentation.common.components.Avatar
import com.umar.chat.utils.toClockTimeString

@Composable
fun ChatBubble(
    message: Message,
    isLoading: Boolean = true
) {
    val colors = MaterialTheme.colorScheme
    val user = message.sender
    val bubbleCornerSize = 16.dp
    val avatarSize = 30.dp
    val spacingBetweenAvatarAndBubble = 8.dp
    val bubbleHorizontalPadding = 12.dp
    val bubbleVerticalPadding = 8.dp
    val bubbleContainerLeftPadding =
        if (!message.isMine && message.nextIsSame) avatarSize + spacingBetweenAvatarAndBubble else 0.dp

    val sharedPlaceholder = Modifier
        .placeholder(
            visible = isLoading,
            shape = RoundedCornerShape(bubbleCornerSize),
            highlight = PlaceholderHighlight.fade(
                highlightColor = colors.surface
            )
        )


    BoxWithConstraints {
        val maxBubbleWidth = maxWidth * 0.75f

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalAlignment = if (message.isMine) Alignment.End else Alignment.Start
        ) {
            Spacer(modifier = Modifier.height(if (message.prevIsSame) 2.dp else 32.dp))

            Row(
                verticalAlignment = Alignment.Bottom,
                modifier = Modifier
                    .padding(start = bubbleContainerLeftPadding)
            ) {
                if (!message.isMine && !message.nextIsSame) {
                    Avatar(
                        image = user.avatarUrl,
                        contentDescription = "User avatar",
                        size = avatarSize,
                        modifier = Modifier
                            .placeholder(
                                visible = isLoading,
                                shape = CircleShape,
                                highlight = PlaceholderHighlight.fade(
                                    highlightColor = colors.surface
                                )
                            )
                    )

                    Spacer(modifier = Modifier.width(spacingBetweenAvatarAndBubble))
                }



                Box {
                    Box(
                        modifier = sharedPlaceholder
                            .wrapContentWidth()
                            .widthIn(max = maxBubbleWidth)
                            .background(
                                color = if (message.isMine) colors.primary else colors.surface,
                                shape = RoundedCornerShape(
                                    topStart = bubbleCornerSize,
                                    topEnd = bubbleCornerSize,
                                    bottomStart = if (message.isMine) bubbleCornerSize else 0.dp,
                                    bottomEnd = if (message.isMine) 0.dp else bubbleCornerSize,
                                ).let { shape ->
                                    if (message.nextIsSame) {
                                        val copy = shape.copy(
                                            bottomStart = CornerSize(bubbleCornerSize),
                                            bottomEnd = CornerSize(bubbleCornerSize)
                                        )
                                        copy
                                    } else shape
                                }
                            )
                            .padding(
                                horizontal = bubbleHorizontalPadding,
                                vertical = bubbleVerticalPadding
                            )
                    ) {
                        Text(
                            text = message.content,
                            color = if (message.isMine) colors.onPrimary else colors.onSurface,
                            textAlign = TextAlign.Start
                        )
                    }

                    if (!message.nextIsSame) {
                        Text(
                            text = message.timestamp.toClockTimeString(),
                            fontSize = 12.sp,
                            color = colors.secondary,
                            modifier = sharedPlaceholder
                                .align(if (message.isMine) Alignment.BottomEnd else Alignment.BottomStart)
                                .offset(
                                    y = 22.dp
                                )
                        )
                    }
                }

            }

        }
    }
}