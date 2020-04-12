package com.example.albums.data.source.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.example.albums.data.domain.Album
import com.example.albums.data.domain.Photo
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class MyDaoTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: MyDatabase
    val albums = listOf(
        Album("album1", "Some album"),
        Album("album2", "Some album"),
        Album("album3", "Some album")
    )
    val photos = listOf(
        Photo("photo1", "album1", "Some photo", "", ""),
        Photo("photo2", "album1", "Some photo", "", ""),
        Photo("photo3", "album2", "Some photo", "", ""),
        Photo("photo4", "album3", "Some photo", "", ""),
        Photo("photo5", "album3", "Some photo", "", "")
    )

    @Before
    fun setUp() = runBlockingTest {
        database =
            Room.inMemoryDatabaseBuilder(getApplicationContext(), MyDatabase::class.java).build()
        database.dao().insertAlbums(*albums.map { DatabaseAlbum.fromAlbum(it) }.toTypedArray())
        database.dao().insertPhotos(*photos.map { DatabasePhoto.fromPhoto(it) }.toTypedArray())
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun getAlbums() = runBlockingTest {
        val data = database.dao().getAlbums().map { it.toAlbum() }
        assertEquals(albums, data)
    }

    @Test
    fun getPhotos() = runBlockingTest {
        val album = albums.first()
        val selectedPhotos = photos.filter { it.albumId == album.id }
        val data = database.dao().getPhotos(album.id).map { it.toPhoto() }
        assertEquals(selectedPhotos, data)
    }
}
