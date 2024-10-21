package com.example.zealcasestudy.api

import jakarta.inject.Inject
import kotlinx.coroutines.flow.flow

class Repository @Inject constructor(private val service: Service) {

    fun getData() = flow {
        emit(service.getData())
    }
}
