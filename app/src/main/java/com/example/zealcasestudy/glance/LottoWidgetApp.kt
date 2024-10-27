package com.example.zealcasestudy.glance

import android.content.Context
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.GlanceTheme
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.cornerRadius
import androidx.glance.appwidget.provideContent
import androidx.glance.background
import androidx.glance.layout.Column
import androidx.glance.layout.wrapContentSize
import androidx.glance.text.Text
import com.example.zealcasestudy.di.EntryPoint
import dagger.hilt.EntryPoints

class LottoWidgetApp : GlanceAppWidget() {

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
                    }
                }
            }
        }
    }
}
