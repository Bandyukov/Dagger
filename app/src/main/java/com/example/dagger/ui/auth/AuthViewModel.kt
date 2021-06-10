package com.example.dagger.ui.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.example.dagger.SessionManager
import com.example.dagger.models.User
import com.example.dagger.network.auth.AuthApi
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AuthViewModel @Inject constructor(
    private val api: AuthApi,
    private val sessionManager: SessionManager
) : ViewModel() {

    fun observeAuthState(): LiveData<AuthResource<User>> = sessionManager.getAuthUser()

    private fun queryUserId(id: Int): LiveData<AuthResource<User>> {
        return LiveDataReactiveStreams
            .fromPublisher(
                api.getData(id)
                    .onErrorReturn {
                        return@onErrorReturn User()
                    }
                    .map(Function {
                        if (it.id == -1) {
                            return@Function AuthResource.error("Could not auth", null)
                        }
                        return@Function AuthResource.authenticated(it)
                    })
                    .subscribeOn(Schedulers.io())
            ) as LiveData<AuthResource<User>>
    }

    fun authenticateById(id: Int) {
        sessionManager.authenticateById(queryUserId(id))
    }

    override fun onCleared() {
        super.onCleared()
    }
}
