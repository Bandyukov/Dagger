package com.example.dagger.network.main

import com.example.dagger.models.Post
import retrofit2.http.GET
import retrofit2.http.Query

interface MainApi {
    @GET("posts")
    fun getUserPosts(@Query(value = "userId") id: Int) : List<Post>

}