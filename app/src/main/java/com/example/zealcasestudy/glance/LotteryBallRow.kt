package com.example.zealcasestudy.glance

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.glance.GlanceModifier
import androidx.glance.appwidget.cornerRadius
import androidx.glance.background
import androidx.glance.layout.Box
import androidx.glance.layout.Row
import androidx.glance.layout.Spacer
import androidx.glance.layout.padding
import androidx.glance.layout.size
import androidx.glance.text.Text
import androidx.glance.text.TextAlign
import androidx.glance.text.TextStyle

@Composable
fun LotteryBallRow(
    numbers: List<Int>,
    specials: List<Int>,
    bottomPadding: Dp = 0.dp
) {
    Row(
        modifier = GlanceModifier
            .background(Color.LightGray)
            .cornerRadius(100.dp)
    ) {
        numbers.forEachIndexed { index, number ->
            LotteryBall(
                number = number.toString(),
                isStart = index == 0
            )
        }
        Row(
            modifier = GlanceModifier
                .background(Color.DarkGray)
                .cornerRadius(100.dp)
        ) {
            specials.forEachIndexed { index, special ->
                LotteryBall(
                    number = special.toString(),
                    isStart = index == 0
                )
            }
        }
    }
    Spacer(modifier = GlanceModifier.padding(bottomPadding))
}

@Composable
fun LotteryBall(
    number: String,
    isStart: Boolean
) {
    val paddingModifier = if (isStart) {
        GlanceModifier.padding(6.dp)
    } else {
        GlanceModifier.padding(top = 6.dp, bottom = 6.dp, end = 6.dp)
    }
    Box(paddingModifier) {
        Text(
            text = number,
            modifier = GlanceModifier
                .padding(2.dp)
                .size(32.dp)
                .cornerRadius(100.dp)
                .background(color = Color.White),
            style = TextStyle(
                fontSize = 18.sp,
                textAlign = TextAlign.Center
            )
        )
    }
}
