package com.example.albums.data.source.remote

import com.example.albums.data.domain.Album
import com.example.albums.data.domain.Photo
import com.example.albums.data.source.DataSource
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val api: Api) : DataSource {
    override suspend fun getAlbums(): List<Album> {
        return api.getAlbums().await()
    }

    override suspend fun getPhotos(albumId: String): List<Photo> {
        return api.getPhotos(albumId).await()
    }
}
