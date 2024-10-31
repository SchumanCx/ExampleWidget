package com.example.zealcasestudy.glance

import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.glance.GlanceTheme
import androidx.glance.appwidget.testing.unit.runGlanceAppWidgetUnitTest
import androidx.glance.testing.unit.hasTestTag
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class GlanceDividerTest {

    private val defaultThickness = 1.dp

    @Test
    fun `GlanceDivider is displayed`() = runGlanceAppWidgetUnitTest {
        setAppWidgetSize(DpSize(100.dp, 10.dp))
        provideComposable {
            GlanceTheme {
                GlanceDivider()
            }
        }
        onNode(hasTestTag("GlanceDivider")).assertExists()
    }

//    @Test
//    fun `GlanceDivider has default thickness`() = runGlanceAppWidgetUnitTest {
//        setAppWidgetSize(DpSize(100.dp, 10.dp))
//        provideComposable {
//            GlanceTheme {
//                GlanceDivider()
//            }
//        }
//        onNode(hasTestTag("GlanceDivider")).assertHeightIsEqualTo(defaultThickness)
//    }
//
//    @Test
//    fun `GlanceDivider with custom thickness`() = runGlanceAppWidgetUnitTest {
//        val customThickness = 5.dp
//        setAppWidgetSize(DpSize(100.dp, 100.dp))
//        provideComposable {
//            GlanceTheme {
//                GlanceDivider(thickness = customThickness)
//            }
//        }
//        onNode(hasTestTag("GlanceDivider")).assertHeightIsEqualTo(customThickness)
//    }
}
