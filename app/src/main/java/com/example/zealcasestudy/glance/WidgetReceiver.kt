package com.example.zealcasestudy.glance

import androidx.glance.appwidget.GlanceAppWidgetReceiver
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WidgetReceiver : GlanceAppWidgetReceiver() {
    override val glanceAppWidget = LottoWidgetApp()
}
