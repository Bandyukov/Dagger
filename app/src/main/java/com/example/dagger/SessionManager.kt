package com.example.dagger

import androidx.annotation.Nullable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.example.dagger.models.User
import com.example.dagger.ui.auth.AuthResource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SessionManager @Inject constructor() {

    private val cashedUser: MediatorLiveData<AuthResource<User>> =
        MediatorLiveData<AuthResource<User>>()

    fun authenticateById(source: LiveData<AuthResource<User>>) {
        cashedUser.value = AuthResource.loading(null)
        cashedUser.addSource(source) {
            cashedUser.value = it
            cashedUser.removeSource(source)
        }
    }

    fun logOut() {
        cashedUser.value = AuthResource.logout()
    }

    fun getAuthUser() : LiveData<AuthResource<User>> = cashedUser

}