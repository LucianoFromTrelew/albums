package com.example.albums.screens.albumdetail

import androidx.lifecycle.*
import com.example.albums.data.Result
import com.example.albums.data.Result.Loading
import com.example.albums.data.Result.Success
import com.example.albums.data.domain.Photo
import com.example.albums.data.source.Repository
import com.example.albums.utils.MyEvent
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

const val ALBUM_DETAIL_VIEW_MODEL_ALBUM_ID = "AlbumDetailViewModel_albumId"

class AlbumDetailViewModel @Inject constructor(
    private val repository: Repository, @Named(
        ALBUM_DETAIL_VIEW_MODEL_ALBUM_ID
    ) private val albumId: String
) : ViewModel() {

    private val _photos = MutableLiveData<Result<List<Photo>>>()
    val photos = _photos.map { (it as? Success)?.data }

    private val _navigateToSelectedPhoto = MutableLiveData<MyEvent<Photo>>()
    val navigateToSelectedPhoto: LiveData<MyEvent<Photo>>
        get() = _navigateToSelectedPhoto

    init {
        _photos.value = Loading
        viewModelScope.launch {
            val photos = repository.getPhotos(albumId)
            _photos.value = photos
        }
    }

    fun onSelectedPhoto(photo: Photo) {
        _navigateToSelectedPhoto.value = MyEvent(photo)
    }
}