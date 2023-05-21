package com.example.animatedgradient.extension

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.*
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@RequiresApi(Build.VERSION_CODES.O)
fun Modifier.drawAnimatedColoredShadow(
    initialColor : Color,
    targetColor : Color,
    durationMillis : Int = 2000,
    alpha: Float = 0.2f,
    borderRadius: Dp = 0.dp,
    shadowRadius: Dp = 20.dp,
    offsetY: Dp = 0.dp,
    offsetX: Dp = 0.dp
): Modifier = composed {
    val infiniteTransition = rememberInfiniteTransition()

    val color by infiniteTransition.animateColor(
        initialValue = initialColor,
        targetValue = targetColor,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )
    drawColoredShadow(
        color,
        alpha,
        borderRadius, shadowRadius, offsetY, offsetX
    )

}