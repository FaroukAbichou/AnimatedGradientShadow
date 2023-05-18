package com.example.animatedgradient

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.animatedgradient.ui.theme.AnimatedGradientTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AnimatedGradientTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    Greeting("Android")
                }
            }
        }
    }
}

@SuppressLint("RememberReturnType")
@Composable
fun Greeting(name: String) {
    val color = remember { Animatable(Color.Gray) }
    LaunchedEffect(Unit) {
        color.animateTo(Color.Red, animationSpec = tween(1000))
        color.animateTo(Color.Gray, animationSpec = tween(1000))
    }
    Box(Modifier.fillMaxSize().background(color.value))
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AnimatedGradientTheme {
        Greeting("Android")
    }
}

