package com.example.albums.screens.albumlist

import androidx.lifecycle.ViewModel
import com.example.albums.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.Subcomponent
import dagger.multibindings.IntoMap

@Subcomponent(modules = [AlbumListModule::class])
interface AlbumListComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): AlbumListComponent
    }

    fun inject(fragment: AlbumListFragment)
}

@Module
abstract class AlbumListModule {

    @Binds
    @IntoMap
    @ViewModelKey(AlbumListViewModel::class)
    abstract fun bindViewModel(viewModel: AlbumListViewModel): ViewModel
}
