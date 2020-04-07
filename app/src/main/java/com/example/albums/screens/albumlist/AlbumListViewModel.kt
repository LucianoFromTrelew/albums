package com.example.albums.screens.albumlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.albums.data.Album


class AlbumListViewModel : ViewModel() {
    private val _albums = MutableLiveData<List<Album>>()
    val albums: LiveData<List<Album>>
        get() = _albums

    init {

        val data = mutableListOf<Album>()
        repeat(100) {
            data.add(Album(it.toString(), "Album $it"))
        }
        _albums.value = data
    }
}