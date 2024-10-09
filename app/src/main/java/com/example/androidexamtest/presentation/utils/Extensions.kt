package com.example.androidexamtest.presentation.utils

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier


@SuppressLint("UnrememberedMutableInteractionSource")
@Composable
fun Modifier.clickableNoRipple(click: (() -> Unit)? = null): Modifier {
    return clickable(
        interactionSource = MutableInteractionSource(),
        indication = null,
        onClick = {
            click?.invoke()
        }
    )
}
