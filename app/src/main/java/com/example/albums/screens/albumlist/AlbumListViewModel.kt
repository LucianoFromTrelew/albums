package com.example.albums.screens.albumlist

import androidx.lifecycle.*
import com.example.albums.data.Result
import com.example.albums.data.Result.*
import com.example.albums.data.domain.Album
import com.example.albums.data.source.Repository
import com.example.albums.utils.MyEvent
import kotlinx.coroutines.launch
import javax.inject.Inject


enum class ApiStatus { LOADING, ERROR, DONE }
class AlbumListViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    private val _albums = MutableLiveData<Result<List<Album>>>()
    val albums = _albums.map { (it as? Success)?.data }
    val status = _albums.map {
        when (it) {
            is Success<*> -> ApiStatus.DONE
            is Loading -> ApiStatus.LOADING
            is Error -> ApiStatus.ERROR
        }

    }

    private val _navigateToSelectedAlbum = MutableLiveData<MyEvent<Album>>()
    val navigateToSelectedAlbum: LiveData<MyEvent<Album>>
        get() = _navigateToSelectedAlbum

    init {
        _albums.value = Loading
        viewModelScope.launch {
            _albums.value = repository.getAlbums()
        }
    }

    fun onAlbumClick(album: Album) {
        _navigateToSelectedAlbum.value = MyEvent(album)
    }
}