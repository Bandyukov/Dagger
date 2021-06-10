package com.example.dagger.DI

import com.example.dagger.DI.auth.AuthModule
import com.example.dagger.DI.auth.AuthViewModelModule
import com.example.dagger.ui.auth.AuthActivity
import com.example.dagger.ui.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {

    @ContributesAndroidInjector(
        modules = [
            AuthViewModelModule::class,
            AuthModule::class,
        ]
    )
    abstract fun contributeAndroidActivity(): AuthActivity

    @ContributesAndroidInjector
    abstract fun contributeMainActivity() : MainActivity
}