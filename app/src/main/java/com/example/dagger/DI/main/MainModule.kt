package com.example.dagger.DI.main

import com.example.dagger.network.main.MainApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class MainModule {
    companion object {
        @Provides
        fun provideMainApi(retrofit: Retrofit) : MainApi = retrofit.create(MainApi::class.java)
    }
}