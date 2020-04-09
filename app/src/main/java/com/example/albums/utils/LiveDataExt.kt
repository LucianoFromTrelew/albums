package com.example.albums.utils

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import com.example.albums.data.Result
import com.example.albums.data.Result.*
import com.example.albums.screens.albumlist.ApiStatus

fun <T: Result<*>> MutableLiveData<T>.mapResultToStatus() = map {
    when (it as Result<*>) {
        is Success<*> -> ApiStatus.DONE
        is Loading -> ApiStatus.LOADING
        is Error -> ApiStatus.ERROR
    }
}
