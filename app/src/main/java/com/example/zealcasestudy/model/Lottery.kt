package com.example.zealcasestudy.model

import com.google.gson.annotations.SerializedName

data class Lottery(
    val lottery: LotteryName,
    val lastDraw: LastDraw,
    val nextDraw: NextDraw
)

enum class LotteryName {
    @SerializedName("6aus49")
    LOTTO,

    @SerializedName("eurojackpot")
    EUROJACKPOT
}
