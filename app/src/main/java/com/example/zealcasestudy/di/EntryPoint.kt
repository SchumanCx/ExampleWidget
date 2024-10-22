package com.example.zealcasestudy.di

import com.example.zealcasestudy.MainViewModel
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface EntryPoint {

    fun viewModel(): MainViewModel
}
