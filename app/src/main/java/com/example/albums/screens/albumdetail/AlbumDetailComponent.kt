package com.example.albums.screens.albumdetail

import androidx.lifecycle.ViewModel
import com.example.albums.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.Subcomponent
import dagger.multibindings.IntoMap

@Subcomponent(modules = [AlbumDetailModule::class])
interface AlbumDetailComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): AlbumDetailComponent
    }

    fun inject(fragment: AlbumDetailFragment)
}

@Module
abstract class AlbumDetailModule {

    @Binds
    @IntoMap
    @ViewModelKey(AlbumDetailViewModel::class)
    abstract fun bindViewModel(viewModel: AlbumDetailViewModel): ViewModel
}
