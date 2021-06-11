package com.example.dagger.ui.main

import androidx.annotation.NonNull
import androidx.annotation.Nullable


class Resource<T>(
    @NonNull
    val status: Status?,
    @Nullable
    val data: T?,
    @Nullable
    val message: String?
) {

    companion object {
        fun <T> success(@Nullable data: T): Resource<T> {
            return Resource(Status.SUCCESS, data, null)
        }

        fun <T> error(msg: String?, @Nullable data: T?): Resource<T> {
            return Resource(Status.ERROR, data, msg)
        }

        fun <T> loading(@Nullable data: T?): Resource<T> {
            return Resource(Status.LOADING, data, null)
        }
    }

    enum class Status {
        SUCCESS, ERROR, LOADING
    }

}