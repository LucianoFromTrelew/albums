package com.example.albums.data.source.remote

import com.example.albums.data.domain.Album
import com.example.albums.data.domain.Photo
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
    @GET("albums")
    fun getAlbums(): Deferred<List<Album>>
    @GET("photos")
    fun getPhotos(@Query("albumId") albumId: String): Deferred<List<Photo>>
}