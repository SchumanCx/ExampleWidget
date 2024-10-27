package com.example.zealcasestudy.glance

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.glance.GlanceModifier
import androidx.glance.appwidget.cornerRadius
import androidx.glance.background
import androidx.glance.layout.Box
import androidx.glance.layout.padding
import androidx.glance.text.FontWeight
import androidx.glance.text.Text
import androidx.glance.text.TextStyle
import androidx.glance.unit.ColorProvider
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Composable
fun FormattedDate(
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