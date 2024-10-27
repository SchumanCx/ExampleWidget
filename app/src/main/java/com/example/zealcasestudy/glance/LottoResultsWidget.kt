package com.example.zealcasestudy.glance

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.glance.GlanceModifier
import androidx.glance.layout.Column
import androidx.glance.layout.Row
import androidx.glance.layout.fillMaxWidth
import androidx.glance.layout.padding
import androidx.glance.text.FontWeight
import androidx.glance.text.Text
import androidx.glance.text.TextStyle
import com.example.zealcasestudy.R
import com.example.zealcasestudy.model.Lottery
import com.example.zealcasestudy.model.LotteryName
import com.example.zealcasestudy.stringResource

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

            NextDrawView(lottery)
        }
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
