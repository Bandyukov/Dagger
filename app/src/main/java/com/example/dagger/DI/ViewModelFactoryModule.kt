package com.example.dagger.DI

import androidx.lifecycle.ViewModelProvider
import com.example.dagger.viewmodels.ViewModelProviderFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModule {

    @Binds
    abstract fun bindViewModelFactory(viewModelProviderFactory: ViewModelProviderFactory) :
            ViewModelProvider.Factory

}