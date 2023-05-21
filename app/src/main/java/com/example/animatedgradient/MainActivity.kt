package com.example.animatedgradient

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.animatedgradient.extension.drawAnimatedColoredShadow
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
                .padding(10.dp)
        ){
            Image(
                painter = painterResource(R.drawable.poke_card),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )

        }

    }
}





