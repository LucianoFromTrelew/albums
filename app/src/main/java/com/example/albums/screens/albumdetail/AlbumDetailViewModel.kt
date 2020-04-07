package com.example.albums.screens.albumdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.albums.data.Photo

class AlbumDetailViewModel : ViewModel() {

    private val _photos = MutableLiveData<List<Photo>>()
    val photos: LiveData<List<Photo>>
        get() = _photos

    init {
        val data = mutableListOf<Photo>()
        repeat(100) {
            data.add(
                Photo("", "", "", "")
            )
        }
        _photos.value = data
    }
}