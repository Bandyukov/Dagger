package com.example.dagger.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Post(
    @SerializedName("userId")
    @Expose
    private val userId: Int,

    @SerializedName("id")
    @Expose
    private val id: Int,

    @SerializedName("title")
    @Expose
    private val title: String,

    @SerializedName("body")
    @Expose
    private val body: String
)