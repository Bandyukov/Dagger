package com.example.dagger.DI.main

import androidx.lifecycle.ViewModel
import com.example.dagger.DI.ViewModelKey
import com.example.dagger.ui.main.post.PostViewModel
import com.example.dagger.ui.main.profile.ProfileViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MainViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(ProfileViewModel::class)
    abstract fun bindProfileViewModel(profileViewModel: ProfileViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PostViewModel::class)
    abstract fun bindPostViewModel(profileViewModel: PostViewModel) : ViewModel
}