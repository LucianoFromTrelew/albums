package com.example.albums.data.source

import com.example.albums.data.domain.Album
import com.example.albums.data.domain.Photo
import com.example.albums.data.Result

interface DataSource {
    suspend fun getAlbums(shouldFetch: Boolean = false): Result<List<Album>>
    suspend fun getPhotos(albumId: String, shouldFetch: Boolean = false): Result<List<Photo>>
}