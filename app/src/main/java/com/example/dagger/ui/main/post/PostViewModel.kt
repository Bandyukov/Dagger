package com.example.dagger.ui.main.post

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.example.dagger.SessionManager
import com.example.dagger.models.Post
import com.example.dagger.network.main.MainApi
import com.example.dagger.ui.main.Resource
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PostViewModel @Inject constructor(
    private val sessionManager: SessionManager,
    private val mainApi: MainApi,
) : ViewModel() {

    private val posts = MediatorLiveData<Resource<List<Post>>>()

    fun observePost(): LiveData<Resource<List<Post>>> {

        posts.value = Resource.loading(null)

        val source: LiveData<Resource<List<Post>>> = LiveDataReactiveStreams
            .fromPublisher(
                mainApi.getUserPosts(sessionManager.getAuthUser().value?.data!!.id)
                    .onErrorReturn {
                        val post = Post()
                        val posts = ArrayList<Post>()
                        posts.add(post)
                        return@onErrorReturn posts
                    }
                    .map(Function {
                        if (it.isNotEmpty()) {
                            if (it[0].id == -1) {
                                return@Function Resource.error("First item id is ${it[0].id}", null)
                            }
                        }
                        return@Function Resource.success(it)
                    })
                    .subscribeOn(Schedulers.io())
            ) as LiveData<Resource<List<Post>>>

        posts.addSource(source) {
            posts.value = it
            posts.removeSource(source)
        }

        return posts
    }


}