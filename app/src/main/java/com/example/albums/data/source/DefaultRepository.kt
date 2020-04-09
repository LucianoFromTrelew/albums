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
    @Named(REPOSITORY_REMOTE_DATA_SOURCE) private val remoteDataSource: DataSource,
    @Named(REPOSITORY_LOCAL_DATA_SOURCE) private val localDataSource: DataSource
) : Repository {
    override suspend fun getAlbums(shouldFetch: Boolean): Result<List<Album>> {
        return try {
            val localAlbums = localDataSource.getAlbums()
            if (localAlbums.isEmpty() || shouldFetch) {
                val remoteAlbums = remoteDataSource.getAlbums()
                localDataSource.insertAlbums(remoteAlbums)
                return Success(remoteAlbums)
            }
            Success(localAlbums)
        } catch (e: Exception) {
            Error(e)
        }
    }

    override suspend fun getPhotos(albumId: String, shouldFetch: Boolean): Result<List<Photo>> {
        return try {
            val localPhotos = localDataSource.getPhotos(albumId)
            if (localPhotos.isEmpty() || shouldFetch) {
                val remotePhotos = remoteDataSource.getPhotos(albumId)
                localDataSource.insertPhotos(remotePhotos)
                return Success(remotePhotos)
            }
            Success(localPhotos)
        } catch (e: Exception) {
            Error(e)
        }
    }
}