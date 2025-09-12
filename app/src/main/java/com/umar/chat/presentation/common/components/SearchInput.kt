package com.umar.chat.presentation.common.components

import android.view.SoundEffectConstants
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.umar.chat.R
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map

@OptIn(FlowPreview::class)
@Composable
fun SearchInput(
    modifier: Modifier = Modifier,
    height: Dp = 48.dp,
    onDebounceDone: (String) -> Unit = {},
    debounceDelay: Long = 500L
) {
    val colors = MaterialTheme.colorScheme
    var text by remember { mutableStateOf("") }
    val view = LocalView.current

    LaunchedEffect(text) {
        snapshotFlow { text }
            .map { it.trim() }
            .distinctUntilChanged()
            .debounce(debounceDelay)
            .collectLatest { debouncedText ->
                onDebounceDone(debouncedText)
            }
    }

    Row(
        modifier = modifier
            .height(height)
            .background(colors.surface, shape = CircleShape)
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(R.drawable.eva_search_outline),
            contentDescription = "Search icon",
            tint = colors.secondary
        )

        Spacer(modifier = Modifier.width(8.dp))

        BasicTextField(
            value = text,
            onValueChange = { text = it },
            textStyle = TextStyle(
                fontSize = 15.sp,
                lineHeight = 20.sp,
                color = colors.onSurface
            ),
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Send
            ),
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null
                ) {
                    view.playSoundEffect(SoundEffectConstants.CLICK)
                },
            decorationBox = { innerTextField ->
                if (text.isEmpty()) {
                    Text(
                        text = "Type a message...",
                        color = colors.secondary,
                        fontSize = 15.sp,
                        lineHeight = 20.sp
                    )
                }

                innerTextField()
            }
        )
    }

}