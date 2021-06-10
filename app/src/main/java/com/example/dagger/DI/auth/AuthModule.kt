package com.example.dagger.DI.auth

import com.example.dagger.network.auth.AuthApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class AuthModule {

    companion object {
        @Provides
        fun provideRetrofit(retrofit: Retrofit) : AuthApi = retrofit.create(AuthApi::class.java)
    }
}