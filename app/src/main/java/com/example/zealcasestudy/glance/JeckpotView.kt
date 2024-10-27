package com.example.zealcasestudy.glance

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.glance.GlanceModifier
import androidx.glance.ImageProvider
import androidx.glance.background
import androidx.glance.layout.Column
import androidx.glance.layout.height
import androidx.glance.layout.padding
import androidx.glance.text.FontWeight
import androidx.glance.text.Text
import androidx.glance.text.TextStyle
import androidx.glance.unit.ColorProvider
import com.example.zealcasestudy.R
import com.example.zealcasestudy.model.LotteryName

@Composable
fun JackpotView(
    name: LotteryName,
    jackpot: Int
) {
    val backgroundModifier = when (name) {
        LotteryName.LOTTO -> GlanceModifier.background(Color.Yellow)
        LotteryName.EUROJACKPOT -> GlanceModifier.background(ImageProvider(R.drawable.jackpot_background))
    }
    val contentColor = when (name) {
        LotteryName.LOTTO -> ColorProvider(Color.Red)
        LotteryName.EUROJACKPOT -> ColorProvider(Color.Yellow)
    }

    Column(
        modifier = backgroundModifier
            .height(220.dp)
            .padding(8.dp)
    ) {
        Text(
            text = (jackpot / 1000000).toString(), // 1 Mio
            style = TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = contentColor
            ),
            modifier = GlanceModifier.padding(top = 8.dp)
        )
        Text(
            text = "Mio",
            style = TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = contentColor
            ),
            modifier = GlanceModifier.padding(end = 8.dp)
        )
    }
}