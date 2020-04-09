package com.example.albums.data.source.local

import com.example.albums.data.domain.Album
import com.example.albums.data.domain.Photo
import com.example.albums.data.source.DataSource

class LocalDataSource constructor(
    private val dao: MyDao
) : DataSource {
    override suspend fun getAlbums(): List<Album> {
        return dao.getAlbums().map { it.toAlbum() }
    }

    override suspend fun insertAlbums(albums: List<Album>) {
        val toInsert = albums.map { DatabaseAlbum.fromAlbum(it) }
        dao.insertAlbums(*toInsert.toTypedArray())
    }

    override suspend fun getPhotos(albumId: String): List<Photo> {
        return dao.getPhotos(albumId).map { it.toPhoto() }
    }

    override suspend fun insertPhotos(photos: List<Photo>) {
        val toInsert = photos.map { DatabasePhoto.fromPhoto(it) }
        dao.insertPhotos(*toInsert.toTypedArray())
    }
}