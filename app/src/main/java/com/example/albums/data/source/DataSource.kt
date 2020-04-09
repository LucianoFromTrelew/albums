package com.example.albums.data.source

import com.example.albums.data.domain.Album
import com.example.albums.data.domain.Photo

interface DataSource {
    suspend fun getAlbums(): List<Album>
    suspend fun insertAlbums(albums: List<Album>)
    suspend fun getPhotos(albumId: String): List<Photo>
    suspend fun insertPhotos(photos: List<Photo>)
}