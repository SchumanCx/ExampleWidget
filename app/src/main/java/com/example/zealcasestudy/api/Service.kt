package com.example.zealcasestudy.api

import com.example.zealcasestudy.model.Lottery
import retrofit2.http.GET

interface Service {
    @GET("aggregated/6aus49,eurojackpot")
    suspend fun getData(): List<Lottery>
}
