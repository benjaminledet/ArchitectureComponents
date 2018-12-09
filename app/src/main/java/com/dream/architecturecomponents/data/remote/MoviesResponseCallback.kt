package com.dream.architecturecomponents.data.remote

interface MoviesResponseCallback {

    fun onSuccess()

    fun onError(throwable: Throwable)
}