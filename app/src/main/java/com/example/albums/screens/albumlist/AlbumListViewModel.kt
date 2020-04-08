package com.example.albums.screens.albumlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.albums.data.Album
import com.example.albums.utils.MyEvent
import javax.inject.Inject


class AlbumListViewModel @Inject constructor() : ViewModel() {

    private val _albums = MutableLiveData<List<Album>>()
    val albums: LiveData<List<Album>>
        get() = _albums


    private val _navigateToSelectedAlbum = MutableLiveData<MyEvent<Album>>()
    val navigateToSelectedAlbum: LiveData<MyEvent<Album>>
        get() = _navigateToSelectedAlbum

    init {
        val data = mutableListOf<Album>()
        repeat(100) {
            data.add(Album(it.toString(), "Album $it"))
        }
        _albums.value = data
    }

    fun onAlbumClick(album: Album) {
        _navigateToSelectedAlbum.value = MyEvent(album)
    }
}