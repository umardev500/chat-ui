package com.umar.chat.presentation.ui.chat.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.eygraber.compose.placeholder.PlaceholderHighlight
import com.eygraber.compose.placeholder.fade
import com.eygraber.compose.placeholder.material3.placeholder
import com.umar.chat.R
import com.umar.chat.presentation.common.components.Avatar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatAppBar(
    name: String,
    photoUrl: String? = null,
    isLoading: Boolean = true
) {
    val colors = MaterialTheme.colorScheme
    val navigator = LocalNavigator.currentOrThrow
    val title = name
    val avatar = photoUrl

    val sharedPlaceholder = Modifier
        .placeholder(
            visible = isLoading,
            highlight = PlaceholderHighlight.fade(
                highlightColor = colors.surface
            )
        )

    Column {
        TopAppBar(
            navigationIcon = {
                IconButton(onClick = {
                    navigator.pop()
                }) {
                    Icon(
                        painter = painterResource(R.drawable.ms_arrow_back),
                        contentDescription = "Back button"
                    )
                }
            },
            title = {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Avatar(
                        image = avatar,
                        contentDescription = "Avatar",
                        size = 35.dp,
                        modifier = Modifier
                            .placeholder(
                                visible = isLoading,
                                shape = CircleShape,
                                highlight = PlaceholderHighlight.fade(
                                    highlightColor = colors.surface
                                )
                            )
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Column {
                        Text(
                            text = title,
                            fontSize = 18.sp,
                            lineHeight = 20.sp,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            modifier = sharedPlaceholder
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = "Online",
                            fontSize = 12.sp,
                            lineHeight = 14.sp,
                            modifier = sharedPlaceholder
                        )
                    }
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = colors.background
            ),
            actions = {
                IconButton(onClick = {}) {
                    Icon(
                        painter = painterResource(R.drawable.ms_phone_enabled),
                        contentDescription = "Call menu"
                    )
                }
                IconButton(onClick = {}) {
                    Icon(
                        painter = painterResource(R.drawable.ms_more_vert),
                        contentDescription = "More vertical menu"
                    )
                }
            }
        )

        HorizontalDivider(
            modifier = Modifier.height(1.dp),
            color = colors.surface,
            thickness = 0.5.dp
        )
    }
}