package com.umar.chat.presentation.ui.chat.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.umar.chat.R

@Composable
fun ChatInput(
    onSend: (String) -> Unit = {}
) {
    var text by remember { mutableStateOf("") }

    // Constants
    val minHeight = 36.dp
    val maxHeight = 156.dp
    val verticalPadding = 4.dp
    val cornerRadius = (minHeight / 2) + verticalPadding
    val colors = MaterialTheme.colorScheme
    val buttonSize = minHeight + verticalPadding * 2
    val isEnabled = text.isNotBlank()

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(modifier = Modifier.width(16.dp))

        // Input box
        Box(
            modifier = Modifier
                .weight(1f)
                .border(
                    1.dp,
                    colors.surface,
                    RoundedCornerShape(cornerRadius)
                )
                .background(
                    colors.surfaceVariant,
                    RoundedCornerShape(cornerRadius)
                )
                .padding(horizontal = 16.dp, vertical = verticalPadding)
                .heightIn(min = minHeight, max = maxHeight)
        ) {
            BasicTextField(
                value = text,
                onValueChange = { text = it },
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .fillMaxWidth(),
                textStyle = TextStyle(
                    color = colors.onBackground,
                    fontSize = 15.sp,
                    lineHeight = 20.sp
                ),
                maxLines = 10,
                decorationBox = { innerTextField ->
                    if (text.isEmpty()) {
                        Text(
                            text = "Type a message...",
                            color = Color.Gray,
                            fontSize = 15.sp,
                            lineHeight = 20.sp
                        )
                    }
                    innerTextField()
                }
            )
        }

        Spacer(modifier = Modifier.width(8.dp))

        // Send button
        IconButton(
            onClick = {
                onSend(text)
                text = ""
            },
            enabled = isEnabled,
            modifier = Modifier
                .size(buttonSize)
                .background(
                    if (isEnabled) colors.primary else colors.onSurface.copy(alpha = 0.15f),
                    CircleShape
                )
        ) {
            Icon(
                painter = painterResource(R.drawable.ms_send),
                contentDescription = "Send",
                tint = if (isEnabled) colors.onPrimary else colors.onSurface.copy(alpha = 0.38f),
                modifier = Modifier.size(24.dp)
            )
        }

        Spacer(modifier = Modifier.width(16.dp))
    }
}
