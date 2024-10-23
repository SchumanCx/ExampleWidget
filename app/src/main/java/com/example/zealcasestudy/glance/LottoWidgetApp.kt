package com.example.zealcasestudy.glance

import android.content.Context
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.GlanceTheme
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.SizeMode
import androidx.glance.appwidget.cornerRadius
import androidx.glance.appwidget.provideContent
import androidx.glance.background
import androidx.glance.layout.Box
import androidx.glance.layout.Column
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.fillMaxWidth
import androidx.glance.layout.height
import androidx.glance.layout.wrapContentSize
import androidx.glance.text.Text
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
                Column(
                    modifier = GlanceModifier
                        .wrapContentSize()
                        .background(MaterialTheme.colorScheme.background)
                        .cornerRadius(24.dp)
                ) {
                    if (data.value == null) {
                        Text("Loading...")
                    } else {
                        val list = data.value
                        list?.forEachIndexed { index, item ->
                            LottoResultsWidget(item)
                            if (index < list.size - 1) {
                                GlanceDivider(
                                    color = Color.LightGray,
                                    thickness = 1.dp
                                )
                            }
                        }
                        // Implement click handling for bonus features (Spiel77, SUPER6) and website/app link
                        // using Modifier.clickable etc.
                    }
                }
            }
        }
    }
}

@Composable
fun GlanceDivider(color: Color = MaterialTheme.colorScheme.onBackground, thickness: Dp = 1.dp) {
    Box(
        modifier = GlanceModifier
            .height(thickness)
            .fillMaxWidth()
            .background(color),
        content = { }
    )
}
