package com.example.albums.data.source

import com.example.albums.data.Result
import com.example.albums.data.Result.Success
import com.example.albums.data.domain.Album
import com.example.albums.data.domain.Photo
import javax.inject.Inject

class FakeRepository @Inject constructor() : Repository {
    override suspend fun getAlbums(shouldFetch: Boolean): Result<List<Album>> {
        val data = mutableListOf<Album>()
        repeat(100) {
            data.add(
                Album(
                    it.toString(),
                    "Album $it"
                )
            )
        }
        return Success(data)
    }

    override suspend fun getPhotos(albumId: String, shouldFetch: Boolean): Result<List<Photo>> {
        val data = mutableListOf<Photo>()
        repeat(100) {
            data.add(
                Photo(
                    "$it",
                    "",
                    "https://via.placeholder.com/150/65ad4f",
                    "https://via.placeholder.com/150/65ad4f"
                )
            )
        }
        return Success(data)
    }
}
