package com.example.zealcasestudy.glance

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.glance.GlanceModifier
import androidx.glance.appwidget.cornerRadius
import androidx.glance.background
import androidx.glance.layout.Column
import androidx.glance.layout.Row
import androidx.glance.layout.Spacer
import androidx.glance.layout.padding
import androidx.glance.layout.size
import androidx.glance.text.FontWeight
import androidx.glance.text.Text
import androidx.glance.text.TextAlign
import androidx.glance.text.TextStyle
import com.example.zealcasestudy.model.Lottery

@Composable
fun LottoResultsWidget(data: List<Lottery>?) {

    Column(
        modifier = GlanceModifier
            .padding(16.dp)
            .background(MaterialTheme.colorScheme.background)
    ) {
        if (data == null) {
            Text("Loading...")
        } else {
            Text(
                text = data[0].lottery + ":",
                style = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
            )
            Spacer(modifier = GlanceModifier.padding(16.dp))

            Text(data[0].nextDraw.drawDate)
            Spacer(modifier = GlanceModifier.padding(8.dp))

            Row {
                LotteryBall(data[0].lastDraw.drawResult.numbers)
                Spacer(modifier = GlanceModifier.padding(16.dp))
                if (data[0].nextDraw.jackpot.jackpotSupported) {
                    Text(data[0].nextDraw.jackpot.jackpots.WC_1)
                }
            }
        }
    }
}

@Composable
fun LotteryBall(numbers: List<Int>) {
    Row(
        modifier = GlanceModifier
            .background(Color.LightGray)
            .padding(6.dp)
            .cornerRadius(100.dp)
    ) {
        numbers.forEachIndexed { index, number ->
            Text(
                text = number.toString(),
                modifier = GlanceModifier
                    .padding(4.dp)
                    .size(32.dp)
                    .cornerRadius(100.dp)
                    .background(color = Color.White),
                style = TextStyle(
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center
                )
            )
            if (index < numbers.size - 1) {
                Spacer(modifier = GlanceModifier.padding(6.dp))
            }
        }
    }
}
