package com.example.albums.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface MyDao {
    @Query("SELECT * FROM albums")
    suspend fun getAlbums(): List<DatabaseAlbum>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAlbums(vararg albums: DatabaseAlbum)

    @Query("SELECT * FROM photos WHERE album_id = :albumId")
    suspend fun getPhotos(albumId: String): List<DatabasePhoto>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPhotos(vararg photos: DatabasePhoto)

}