package com.example.zealcasestudy.model

data class NextDraw(
    val drawIdentifier: String,
    val lottery: String,
    val drawDate: String,
    val dataDrawUTC: String,
    val timeZone: String,
    val cutofftime: String,
    val jackpot: Jackpot
)
