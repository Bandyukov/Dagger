package com.example.dagger.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName(value = "id")
    @Expose
    val id: Int = -1,

    @SerializedName(value = "username")
    @Expose
    val username: String = String(),

    @SerializedName(value = "email")
    @Expose
    val email: String = String(),

    @SerializedName(value = "website")
    @Expose
    val website: String = String(),
)