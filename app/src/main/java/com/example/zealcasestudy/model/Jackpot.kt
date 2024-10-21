package com.example.zealcasestudy.model

data class Jackpot(
    val drawIdentifier: String,
    val lottery: String,
    val drawDate: String,
    val currency: String,
    val jackpots: Jackpots,
    val jackpotSupported: Boolean
)
