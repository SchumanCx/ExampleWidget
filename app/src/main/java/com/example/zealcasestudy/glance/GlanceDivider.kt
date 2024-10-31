package com.example.zealcasestudy.glance

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.glance.GlanceModifier
import androidx.glance.background
import androidx.glance.layout.Box
import androidx.glance.layout.fillMaxWidth
import androidx.glance.layout.height
import androidx.glance.semantics.semantics
import androidx.glance.semantics.testTag

@Composable
fun GlanceDivider(color: Color = MaterialTheme.colorScheme.onBackground, thickness: Dp = 1.dp) {
    Box(
        modifier = GlanceModifier
            .height(thickness)
            .fillMaxWidth()
            .background(color)
            .semantics {
                testTag = "GlanceDivider"
            },
        content = { }
    )
}
