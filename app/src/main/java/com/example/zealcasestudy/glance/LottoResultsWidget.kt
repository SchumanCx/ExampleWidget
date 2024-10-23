package com.example.zealcasestudy.glance

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.glance.GlanceModifier
import androidx.glance.ImageProvider
import androidx.glance.LocalContext
import androidx.glance.appwidget.cornerRadius
import androidx.glance.background
import androidx.glance.layout.Box
import androidx.glance.layout.Column
import androidx.glance.layout.Row
import androidx.glance.layout.Spacer
import androidx.glance.layout.fillMaxWidth
import androidx.glance.layout.height
import androidx.glance.layout.padding
import androidx.glance.layout.size
import androidx.glance.text.FontWeight
import androidx.glance.text.Text
import androidx.glance.text.TextAlign
import androidx.glance.text.TextStyle
import androidx.glance.unit.ColorProvider
import com.example.zealcasestudy.R
import com.example.zealcasestudy.model.Lottery
import com.example.zealcasestudy.model.LotteryName
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Composable
fun LottoResultsWidget(lottery: Lottery) {

    Row(modifier = GlanceModifier.fillMaxWidth()) {
        if (lottery.nextDraw.jackpot.jackpotSupported) {
            JackpotView(
                lottery.lottery,
                lottery.nextDraw.jackpot.jackpots.WC_1.toDouble().toInt()
            )// Assuming WC_1 is a valid key
        }

        Column(
            modifier = GlanceModifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = if (lottery.lottery == LotteryName.LOTTO) stringResource(R.string.lotto_6aus49)
                else stringResource(R.string.eurojackpot),
                style = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                ),
                modifier = GlanceModifier.padding(bottom = 12.dp)
            )

            FormattedDate(drawDate = lottery.lastDraw.drawDate, contentColor = Color.Gray)

            LotteryBallRow(
                numbers = lottery.lastDraw.drawResult.numbers,
                specials = getSpecialNumbers(lottery),
                bottomPadding = 16.dp
            )

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
    }
}

@Composable
private fun JackpotView(
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

@Composable
private fun FormattedDate(
    drawDate: String,
    contentColor: Color,
    backgroundColor: Color = Color.Transparent
) {
    val formatter =
        DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX") // Includes time and time zone offset
    val dateTime = LocalDateTime.parse(drawDate, formatter)
    val formattedDateTime =
        dateTime.format(DateTimeFormatter.ofPattern("EEE, dd.MM.yyyy")) // Format for display

    Box(
        modifier = GlanceModifier
            .background(backgroundColor)
            .cornerRadius(4.dp)
            .padding(horizontal = 8.dp)
    )  {
        Text(
            text = formattedDateTime,
            style = TextStyle(
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = ColorProvider(contentColor)
            ),
            modifier = GlanceModifier.padding(bottom = 8.dp)
        )
    }
}

private fun getSpecialNumbers(lottery: Lottery): List<Int> = when {
    lottery.lastDraw.drawResult.superNumber != null -> {
        listOf(lottery.lastDraw.drawResult.superNumber)
    }

    lottery.lastDraw.drawResult.euroNumbers != null -> {
        lottery.lastDraw.drawResult.euroNumbers
    }

    else -> {
        emptyList()
    }
}

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

@Composable
@ReadOnlyComposable
fun stringResource(@StringRes id: Int): String {
    return LocalContext.current.getString(id)
}