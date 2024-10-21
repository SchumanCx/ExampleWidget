package com.example.zealcasestudy.model

data class LastDraw(
    val drawIdentifier: String,
    val lottery: String,
    val drawDate: String,
    val dataDrawUTC: String,
    val drawResult: DrawResult,
    val quotas: Quotas,
    val winners: Winners,
    val totalStrike: String,
    val currency: String
)
