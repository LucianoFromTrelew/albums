package com.example.albums.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [DatabaseAlbum::class, DatabasePhoto::class],
    version = 1,
    exportSchema = false
)
abstract class MyDatabase : RoomDatabase() {
    abstract fun dao(): MyDao
}