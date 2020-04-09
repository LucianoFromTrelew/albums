package com.example.albums.data.source

import com.example.albums.data.Result
import com.example.albums.data.Result.Error
import com.example.albums.data.Result.Success
import com.example.albums.data.domain.Album
import com.example.albums.data.domain.Photo
import javax.inject.Inject
import javax.inject.Named

const val REPOSITORY_REMOTE_DATA_SOURCE = "Repository_RemoteDataSource"
const val REPOSITORY_LOCAL_DATA_SOURCE = "Repository_LocalDataSource"

class DefaultRepository @Inject constructor(
    @Named(REPOSITORY_REMOTE_DATA_SOURCE) private val remoteDataSource: DataSource
) : Repository {
    override suspend fun getAlbums(shouldFetch: Boolean): Result<List<Album>> {
        return try {
            Success(remoteDataSource.getAlbums())
        } catch (e: Exception) {
            Error(e)
        }
    }

    override suspend fun getPhotos(albumId: String, shouldFetch: Boolean): Result<List<Photo>> {
        return try {
            Success(remoteDataSource.getPhotos(albumId))
        } catch (e: Exception) {
            Error(e)
        }
    }
}