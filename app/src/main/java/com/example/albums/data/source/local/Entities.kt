package com.example.albums.data.source.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.albums.data.domain.Album
import com.example.albums.data.domain.Photo

@Entity(tableName = "albums")
data class DatabaseAlbum(
    @PrimaryKey @ColumnInfo(name = "album_id") val albumId: String,
    val title: String
) {
    fun toAlbum() = Album(albumId, title)

    companion object {
        fun fromAlbum(album: Album) = DatabaseAlbum(album.id, album.title)
    }
}

@Entity(
    tableName = "photos",
    foreignKeys = [ForeignKey(
        entity = DatabaseAlbum::class,
        parentColumns = ["album_id"],
        childColumns = ["album_id"]
    )]
)
data class DatabasePhoto(
    @PrimaryKey @ColumnInfo(name = "photo_id") val photoId: String,
    @ColumnInfo(name = "album_id") val albumId: String,
    @ColumnInfo(name = "thumbnail_url") val thumbnailUrl: String,
    val title: String,
    val url: String
) {
    fun toPhoto() = Photo(photoId, albumId, title, url, thumbnailUrl)

    companion object {
        fun fromPhoto(photo: Photo) =
            DatabasePhoto(photo.id, photo.albumId, photo.thumbnailUrl, photo.title, photo.url)
    }
}