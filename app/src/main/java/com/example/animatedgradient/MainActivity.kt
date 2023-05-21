package com.example.animatedgradient

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.Animatable
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.animatedgradient.ui.theme.AnimatedGradientTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AnimatedGradientTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    Greeting(
                        colorPalette
                    )
                }
            }
        }
    }
}

@SuppressLint("RememberReturnType")
@Composable
fun Greeting(
    colorPalette : List<Color>
) {
    val infiniteTransition = rememberInfiniteTransition()
    val color1 by infiniteTransition.animateColor(
        initialValue = colorPalette[0],
        targetValue = colorPalette[1],
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )
    val color2 by infiniteTransition.animateColor(
        initialValue = colorPalette[3],
        targetValue = colorPalette[1],
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )
    val color3 by infiniteTransition.animateColor(
        initialValue = colorPalette[2],
        targetValue = colorPalette[3],
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )
    val color4 by infiniteTransition.animateColor(
        initialValue = colorPalette[0],
        targetValue = colorPalette[3],
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )
    val colorPalette2 = listOf<Color>(
        color2,
        color3,
        color1,
        color4
    )

    BoxWithConstraints(
        modifier = Modifier
            .size(size = 300.dp)
            .background(
                brush = Brush.verticalGradient(
                    colors = colorPalette2,
                    startY = 30f,
                    endY = 200f,

                )
            )
    ) {

    }
}

val colorPalette = listOf<Color>(
    Color(0xff0b0742),
    Color(0xff120c6e),
    Color(0xffff9191),
    Color(0xfffdc094),

)


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AnimatedGradientTheme {
//        Greeting()
    }
}

