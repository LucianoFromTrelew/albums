package com.example.albums.screens.albumdetail

import MainCoroutineRule
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.albums.data.domain.Album
import com.example.albums.data.domain.Photo
import com.example.albums.data.source.FakeTestRepository
import com.example.albums.getOrAwaitValue
import com.example.albums.screens.albumlist.ApiStatus
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class AlbumDetailViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    private lateinit var vm: AlbumDetailViewModel
    private lateinit var repo: FakeTestRepository
    private lateinit var album1: Album
    private lateinit var album2: Album
    private lateinit var photo1: Photo
    private lateinit var photo2: Photo
    private lateinit var photo3: Photo

    @Before
    fun setUp() {
        album1 = Album("album1", "Some title")
        album2 = Album("album2", "Some title")
        photo1 = Photo("photo1", album1.id, "Some photo", "", "")
        photo2 = Photo("photo2", album2.id, "Some photo", "", "")
        photo3 = Photo("photo3", album1.id, "Some photo", "", "")
        repo = FakeTestRepository()
        repo.addAlbums(album1, album2)
        repo.addPhotos(photo1, photo2, photo3)
        vm = AlbumDetailViewModel(repo, album1)
    }

    @Test
    fun loadPhotos_returnsPhotosCorrectly() {
        val data = vm.photos.getOrAwaitValue()
        assertEquals(listOf(photo1, photo3), data)
    }

    @Test
    fun loadPhotos_showsLoading() {
        mainCoroutineRule.pauseDispatcher()
        val vm = AlbumDetailViewModel(repo, album1)
        assertEquals(vm.status.getOrAwaitValue(), ApiStatus.LOADING)
        mainCoroutineRule.resumeDispatcher()
        assertEquals(vm.status.getOrAwaitValue(), ApiStatus.DONE)
    }

    @Test
    fun loadPhotos_showsError() {
        mainCoroutineRule.pauseDispatcher()
        repo.shouldReturnError = true
        val vm = AlbumDetailViewModel(repo, album1)
        assertEquals(vm.status.getOrAwaitValue(), ApiStatus.LOADING)
        mainCoroutineRule.resumeDispatcher()
        assertEquals(vm.status.getOrAwaitValue(), ApiStatus.ERROR)
    }


    @Test
    fun onPhotoClick_setsNavigateToSelectedPhotoCorrectly() {
        vm.onSelectedPhoto(photo1)
        val value = vm.navigateToSelectedPhoto.getOrAwaitValue()
        assertEquals(value.getContentIfNotHandled(), photo1)
    }


}