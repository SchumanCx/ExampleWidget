package com.example.zealcasestudy.glance

import android.content.Context
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.GlanceTheme
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.SizeMode
import androidx.glance.appwidget.provideContent
import androidx.glance.layout.Box
import androidx.glance.layout.fillMaxSize
import com.example.zealcasestudy.di.EntryPoint
import dagger.hilt.EntryPoints

class LottoWidgetApp : GlanceAppWidget() {

    companion object {
        private val SMALL_SQUARE = DpSize(100.dp, 100.dp)
        private val HORIZONTAL_RECTANGLE = DpSize(250.dp, 100.dp)
        private val BIG_SQUARE = DpSize(250.dp, 250.dp)
    }

    override val sizeMode = SizeMode.Responsive(
        setOf(
            SMALL_SQUARE,
            HORIZONTAL_RECTANGLE,
            BIG_SQUARE
        )
    )

    override suspend fun provideGlance(context: Context, id: GlanceId) {

        provideContent {
            val viewModel = EntryPoints.get(
                context,
                EntryPoint::class.java
            ).viewModel()

            val data = viewModel.data.collectAsState()

            GlanceTheme {
                Box(modifier = GlanceModifier.fillMaxSize()) {
                    LottoResultsWidget(data.value)
                    //if (data != null) {
                    // Implement click handling for bonus features (Spiel77, SUPER6) and website/app link
                    // using Modifier.clickable etc.
                    //}
                }
            }
        }
    }
}
