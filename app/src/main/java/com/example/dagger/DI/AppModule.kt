package com.example.dagger.DI

import android.app.Application
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.example.dagger.R
import com.example.dagger.utils.Constants
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class AppModule {

    companion object {
        @Provides
        @Singleton
        fun provideRequestOption(): RequestOptions =
            RequestOptions
                .placeholderOf(R.drawable.ic_bg)
                .error(R.drawable.ic_bg)


        @Provides
        @Singleton
        fun provideGlideInstance(
            application: Application,
            requestOptions: RequestOptions
        ): RequestManager = Glide.with(application).setDefaultRequestOptions(requestOptions)


        @Provides
        @Singleton
        fun provideAppDrawable(application: Application) : Drawable =
            ContextCompat.getDrawable(application, android.R.drawable.ic_dialog_alert)!!


        @Provides
        @Singleton
        fun provideRetrofitInstance() : Retrofit =
            Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }
}