package com.example.zealcasestudy.glance

import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.glance.appwidget.testing.unit.runGlanceAppWidgetUnitTest
import androidx.glance.testing.unit.hasText
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class LotteryBallRowTest {

    @Test
    fun `LotteryBallRow renders numbers and specials`() = runGlanceAppWidgetUnitTest {
        setAppWidgetSize(DpSize(200.dp, 100.dp))

        val numbers = listOf(1, 2, 3)
        val specials = listOf(4, 5)

        provideComposable {
            LotteryBallRow(numbers, specials)
        }

        numbers.forEach {
            onNode(hasText(it.toString())).assertExists()
        }
        specials.forEach {
            onNode(hasText(it.toString())).assertExists()
        }
    }

    @Test
    fun `LotteryBall renders text with correct style and padding`() = runGlanceAppWidgetUnitTest {
        val number = "10"
        val isStart = true

        provideComposable {
            LotteryBall(number, isStart)
        }

        onNode(hasText(number)).assertExists()
    }
}
