package com.example.zealcasestudy.glance

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.glance.GlanceModifier
import androidx.glance.appwidget.cornerRadius
import androidx.glance.background
import androidx.glance.layout.Column
import androidx.glance.layout.Row
import androidx.glance.layout.Spacer
import androidx.glance.layout.fillMaxWidth
import androidx.glance.layout.padding
import androidx.glance.layout.size
import androidx.glance.text.FontWeight
import androidx.glance.text.Text
import androidx.glance.text.TextAlign
import androidx.glance.text.TextStyle
import androidx.glance.unit.ColorProvider
import com.example.zealcasestudy.model.Lottery
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Composable
fun LottoResultsWidget(data: List<Lottery>?) {

    Column(
        modifier = GlanceModifier
            .padding(16.dp)
            .background(MaterialTheme.colorScheme.background)
            .fillMaxWidth()
    ) {
        if (data == null) {
            Text("Loading...")
        } else {
            Text(
                text = data[0].lottery + ":",
                style = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                ),
                modifier = GlanceModifier.padding(bottom = 12.dp)
            )

            val formatter =
                DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX") // Includes time and time zone offset
            val dateTime = LocalDateTime.parse(data[0].lastDraw.drawDate, formatter)
            val formattedDateTime =
                dateTime.format(DateTimeFormatter.ofPattern("EEE, dd.MM.yyyy")) // Format for display
            Text(
                text = formattedDateTime,
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = ColorProvider(Color.DarkGray)
                ),
                modifier = GlanceModifier.padding(bottom = 8.dp)
            )

            LotteryBall(
                numbers = data[0].lastDraw.drawResult.numbers,
                bottomPadding = 16.dp
            )
            if (data[0].nextDraw.jackpot.jackpotSupported) {
                Text(
                    text = "Jackpot: " + data[0].nextDraw.jackpot.jackpots.WC_1,
                    style = TextStyle(
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = ColorProvider(Color.Red)
                    ),
                    modifier = GlanceModifier
                        .background(Color.Yellow)
                        .padding(8.dp)
                        .cornerRadius(16.dp)
                )
            }
        }
    }
}

@Composable
fun LotteryBall(
    numbers: List<Int>,
    bottomPadding: Dp = 0.dp
) {
    Row(
        modifier = GlanceModifier
            .padding(6.dp)
            .background(Color.LightGray)
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
    Spacer(modifier = GlanceModifier.padding(bottomPadding))
}
