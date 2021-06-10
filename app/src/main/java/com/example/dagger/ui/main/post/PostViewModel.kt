package com.example.dagger.ui.main.post

import androidx.lifecycle.ViewModel
import com.example.dagger.SessionManager
import com.example.dagger.network.main.MainApi
import javax.inject.Inject

class PostViewModel @Inject constructor(
    private val sessionManager: SessionManager,
    private val mainApi: MainApi,
) : ViewModel() {

}