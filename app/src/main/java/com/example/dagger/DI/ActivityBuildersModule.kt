package com.example.dagger.DI

import com.example.dagger.DI.auth.AuthModule
import com.example.dagger.DI.auth.AuthViewModelModule
import com.example.dagger.DI.main.MainFragmentBuilderModule
import com.example.dagger.DI.main.MainModule
import com.example.dagger.DI.main.MainViewModelModule
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

    @ContributesAndroidInjector(
        modules = [
            MainFragmentBuilderModule::class,
            MainViewModelModule::class,
            MainModule::class,
        ]
    )
    abstract fun contributeMainActivity() : MainActivity
}