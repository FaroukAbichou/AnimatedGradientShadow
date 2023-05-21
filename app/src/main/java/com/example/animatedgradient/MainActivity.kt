package com.example.animatedgradient

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.animatedgradient.ui.theme.AnimatedGradientTheme

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AnimatedGradientTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    AnimatedShadowCard()
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AnimatedShadowCard(){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)

    ){
        val color1 = Color(0xfffa300c)
        val color2 = Color(0xff48d7ff)

        Box  (

            modifier = Modifier
                .align(Alignment.Center)
                .drawAnimatedColoredShadow(
                    color1,
                    color2,
                    offsetY = 0.dp,
                    offsetX = 0.dp,
                    shadowRadius = 60.dp,
                    borderRadius = 20.dp,
                    alpha = 0.4f
                )
        ){
            Image(
                painter = painterResource(R.drawable.poke_card),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )

        }

    }
}

@RequiresApi(Build.VERSION_CODES.O)
fun Modifier.drawAnimatedColoredShadow(
    initialColor : Color,
    targetColor : Color,
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
            animation = tween(1000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )
    drawColoredShadow(
        color,
        alpha,
        borderRadius, shadowRadius, offsetY, offsetX
    )

}

val colorPalette = listOf<Color>(
    Color(0xffD1D5FA),
    Color(0xffFDB3CA),
    Color(0xfffdc094),

)


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AnimatedGradientTheme {
//        Greeting()
    }
}


@RequiresApi(Build.VERSION_CODES.O)
fun Modifier.drawColoredShadow(
    color: Color,
    alpha: Float = 0.2f,
    borderRadius: Dp = 0.dp,
    shadowRadius: Dp = 20.dp,
    offsetY: Dp = 0.dp,
    offsetX: Dp = 0.dp
) = this.drawBehind {
    val transparentColor = android.graphics.Color.toArgb(
        color.copy(alpha = 0.0f).value.toLong()
    )
    val shadowColor = android.graphics.Color.toArgb(
        color.copy(alpha = alpha).value.toLong()
    )
    this.drawIntoCanvas {
        val paint = Paint()
        val frameworkPaint = paint.asFrameworkPaint()
        frameworkPaint.color = transparentColor
        frameworkPaint.setShadowLayer(
            shadowRadius.toPx(),
            offsetX.toPx(),
            offsetY.toPx(),
            shadowColor
        )

        it.drawRoundRect(
            0f,
            0f,
            this.size.width,
            this.size.height,
            borderRadius.toPx(),
            borderRadius.toPx(),
            paint
        )
    }
}
