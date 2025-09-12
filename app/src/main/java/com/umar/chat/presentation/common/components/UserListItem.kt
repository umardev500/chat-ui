package com.umar.chat.presentation.common.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.umar.chat.R
import com.umar.chat.domain.model.User
import com.umar.chat.utils.clickWithFeedBack

@Composable
fun UserListItem(
    user: User,
    onClick: (User) -> Unit = {}
) {
    val colors = MaterialTheme.colorScheme
    LocalHapticFeedback.current
    LocalView.current

    Row(
        modifier = Modifier
            .background(colors.background)
            .clickWithFeedBack {
                onClick(user)
            }
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            modifier = Modifier
                .weight(1f),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Avatar(
                image = user.avatarUrl,
                contentDescription = "User avatar",
                size = 56.dp
            )

            Spacer(modifier = Modifier.padding(horizontal = 8.dp))

            Column {
                Text(
                    text = user.name,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Medium,
                    color = colors.onBackground
                )
                Text(
                    text = user.email,
                    fontSize = 12.sp,
                    color = colors.secondary
                )
            }
        }


        Icon(
            painter = painterResource(R.drawable.ms_arrow_forward),
            contentDescription = "Go to chat",
            modifier = Modifier
                .size(20.dp)
        )

    }
}