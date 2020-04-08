package com.example.albums.data.source.remote

import com.example.albums.data.Result
import com.example.albums.data.Result.Error
import com.example.albums.data.Result.Success
import com.example.albums.data.domain.Album
import com.example.albums.data.domain.Photo
import com.example.albums.data.source.DataSource
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val api: Api) : DataSource {
    override suspend fun getAlbums(shouldFetch: Boolean): Result<List<Album>> {
        return try {
            val albums = api.getAlbums().await()
            Success(albums)
        } catch (e: Exception) {
            Error(e)
        }
    }

    override suspend fun getPhotos(albumId: String, shouldFetch: Boolean): Result<List<Photo>> {
        return try {
            val photos = api.getPhotos(albumId).await()
            Success(photos)
        } catch (e: Exception) {
            Error(e)
        }
    }
}
