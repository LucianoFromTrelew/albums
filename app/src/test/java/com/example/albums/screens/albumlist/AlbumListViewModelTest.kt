package com.example.albums.screens.albumlist

import MainCoroutineRule
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.albums.data.domain.Album
import com.example.albums.data.source.FakeTestRepository
import com.example.albums.getOrAwaitValue
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class AlbumListViewModelTest {


    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()
    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    private lateinit var vm: AlbumListViewModel
    private lateinit var repo: FakeTestRepository
    private val albums = listOf(
        Album("Album 1", "Some album"),
        Album("Album 2", "Some album"),
        Album("Album 3", "Some album")
    )

    @Before
    fun setUp() {
        repo = FakeTestRepository()
        repo.addAlbums(*albums.toTypedArray())
        vm = AlbumListViewModel(repo)
    }

    @Test
    fun loadAlbums_retursAlbumsCorrectly() {
        val data = vm.albums.getOrAwaitValue()
        assertEquals(albums, data)
    }

    @Test
    fun onAlbumClick_setsNavigateToSelectedAlbumToCorrectAlbum() {
        val album = repo.albumsServiceData.values.first()
        vm.onAlbumClick(album)
        val value = vm.navigateToSelectedAlbum.getOrAwaitValue()
        assertEquals(value.getContentIfNotHandled(), album)
    }

    @Test
    fun loadAlbums_showsLoading() {
        mainCoroutineRule.pauseDispatcher()
        val vm = AlbumListViewModel(repo)
        assertEquals(vm.status.getOrAwaitValue(), ApiStatus.LOADING)
        mainCoroutineRule.resumeDispatcher()
        assertEquals(vm.status.getOrAwaitValue(), ApiStatus.DONE)
    }

    @Test
    fun loadAlbums_showsError() {
        mainCoroutineRule.pauseDispatcher()
        repo.shouldReturnError = true
        val vm = AlbumListViewModel(repo)
        assertEquals(vm.status.getOrAwaitValue(), ApiStatus.LOADING)
        mainCoroutineRule.resumeDispatcher()
        assertEquals(vm.status.getOrAwaitValue(), ApiStatus.ERROR)
    }
}