package com.example.albums.screens.albumdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.albums.data.Photo
import com.example.albums.utils.MyEvent

class AlbumDetailViewModel : ViewModel() {

    private val _photos = MutableLiveData<List<Photo>>()
    val photos: LiveData<List<Photo>>
        get() = _photos
    private val _navigateToSelectedPhoto = MutableLiveData<MyEvent<Photo>>()
    val navigateToSelectedPhoto: LiveData<MyEvent<Photo>>
        get() = _navigateToSelectedPhoto

    init {
        val data = mutableListOf<Photo>()
        repeat(100) {
            data.add(
                Photo("$it", "", "", "")
            )
        }
        _photos.value = data
    }

    fun onSelectedPhoto(photo: Photo) {
        _navigateToSelectedPhoto.value = MyEvent(photo)
    }
}