package com.umar.chat.presentation.ui.thread.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.umar.chat.R
import com.umar.chat.presentation.common.components.withClickFeedback


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ThreadAppBar(
    onSearchClick: () -> Unit = {},
) {
    val colors = MaterialTheme.colorScheme
    var expanded by remember { mutableStateOf(false) }

    Column {
        TopAppBar(
            title = {
                Text(
                    text = "Messaging",
                )
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = colors.background
            ),
            actions = {
                IconButton(onClick = withClickFeedback(onClick = onSearchClick)) {
                    Icon(
                        painter = painterResource(R.drawable.eva_search_outline),
                        contentDescription = "More vertical menu"
                    )
                }
                IconButton(onClick = { expanded = !expanded }) {
                    Icon(
                        painter = painterResource(R.drawable.ms_more_vert),
                        contentDescription = "More vertical menu"
                    )
                }
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = !expanded },
                    modifier = Modifier
                        .widthIn(min = 150.dp)
                        .background(colors.background)
                ) {
                    DropdownMenuItem(
                        leadingIcon = {
                            Icon(
                                painter = painterResource(R.drawable.ms_settings_backup_restore),
                                contentDescription = "Icon"
                            )
                        },
                        text = {
                            Text(
                                text = "Profile"
                            )
                        },
                        onClick = {},
                        contentPadding = PaddingValues(horizontal = 16.dp)
                    )
                    DropdownMenuItem(
                        leadingIcon = {
                            Icon(
                                painter = painterResource(R.drawable.ms_mode_off_on),
                                contentDescription = "Icon"
                            )
                        },
                        text = {
                            Text(
                                text = "Sign Out"
                            )
                        },
                        onClick = {},
                        contentPadding = PaddingValues(horizontal = 16.dp)
                    )
                }
            }
        )
        HorizontalDivider(
            color = colors.surface,
            thickness = 0.5.dp
        )
    }
}