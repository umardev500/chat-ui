package com.umar.chat.utils

import android.view.SoundEffectConstants
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.platform.LocalView

fun Modifier.clickWithFeedBack(onClick: () -> Unit): Modifier = composed {
    val haptic = LocalHapticFeedback.current
    val view = LocalView.current

    clickable(
        interactionSource = remember { MutableInteractionSource() },
    ) {
        view.playSoundEffect(SoundEffectConstants.CLICK)
        haptic.performHapticFeedback(HapticFeedbackType.TextHandleMove)
        onClick()
    }
}