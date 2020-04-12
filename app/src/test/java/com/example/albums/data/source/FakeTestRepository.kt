package com.example.albums.data.source

import com.example.albums.data.Result
import com.example.albums.data.domain.Album
import com.example.albums.data.domain.Photo

open class FakeTestRepository : Repository {
    var shouldReturnError = false
    var albumsServiceData: LinkedHashMap<String, Album> = LinkedHashMap()
    var photosServiceData: LinkedHashMap<String, Photo> = LinkedHashMap()

    override suspend fun getAlbums(shouldFetch: Boolean): Result<List<Album>> {
        return if (shouldReturnError) Result.Error(Exception("Test exception"))
        else Result.Success(albumsServiceData.values.toList())
    }

    override suspend fun getPhotos(albumId: String, shouldFetch: Boolean): Result<List<Photo>> {
        return if (shouldReturnError) Result.Error(Exception("Test exception"))
        else Result.Success(photosServiceData.values.toList())
    }

    fun addAlbums(vararg albums: Album) {
        for (album in albums) {
            albumsServiceData[album.id] = album
        }
    }

    fun addPhotos(vararg photos: Photo) {
        for (photo in photos) {
            photosServiceData[photo.id] = photo
        }
    }
}