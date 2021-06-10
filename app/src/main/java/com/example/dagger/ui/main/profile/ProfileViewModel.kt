package com.example.dagger.ui.main.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.dagger.SessionManager
import com.example.dagger.models.User
import com.example.dagger.ui.auth.AuthResource
import javax.inject.Inject

class ProfileViewModel @Inject constructor(
    private val sessionManager: SessionManager
) : ViewModel() {

    fun getAuthenticatedUser() : LiveData<AuthResource<User>> {
        return sessionManager.getAuthUser()
    }

}