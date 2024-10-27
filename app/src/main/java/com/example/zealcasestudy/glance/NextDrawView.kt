package com.example.zealcasestudy.glance

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.glance.GlanceModifier
import androidx.glance.layout.Row
import androidx.glance.layout.padding
import androidx.glance.text.FontWeight
import androidx.glance.text.Text
import androidx.glance.text.TextStyle
import androidx.glance.unit.ColorProvider
import com.example.zealcasestudy.model.Lottery
import com.example.zealcasestudy.model.LotteryName

@Composable
fun NextDrawView(lottery: Lottery) {
    Row {
        Text(
            text = "Nächste Ziehung: ",
            style = TextStyle(
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = ColorProvider(Color.Gray)
            ),
            modifier = GlanceModifier.padding(bottom = 8.dp)
        )
        val textColor = when (lottery.lottery) {
            LotteryName.LOTTO -> Color.Red
            LotteryName.EUROJACKPOT -> Color.Yellow
        }

        val backgroundColor = when (lottery.lottery) {
            LotteryName.LOTTO -> Color.Yellow
            LotteryName.EUROJACKPOT -> Color.DarkGray
        }
        FormattedDate(
            drawDate = lottery.nextDraw.drawDate,
            contentColor = textColor,
            backgroundColor = backgroundColor
        )
    }
}