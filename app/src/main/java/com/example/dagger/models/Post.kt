package com.example.dagger.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Post(
    @SerializedName("userId")
    @Expose
    val userId: Int = -1,

    @SerializedName("id")
    @Expose val id: Int = -1,

    @SerializedName("title")
    @Expose
    val title: String = String(),

    @SerializedName("body")
    @Expose
    val body: String = String()
)