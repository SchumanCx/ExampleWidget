package com.example.zealcasestudy.di

import com.example.zealcasestudy.api.Service
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApiModule {

    private companion object {
        private const val BASE_URL = "https://www.lotto24.de/public/v1/drawinfo/"
    }

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun provideService(retrofit: Retrofit): Service = retrofit.create(Service::class.java)

}
