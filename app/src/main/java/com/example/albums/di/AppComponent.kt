package com.example.albums.di

import com.example.albums.screens.albumdetail.AlbumDetailComponent
import com.example.albums.screens.albumlist.AlbumListComponent
import dagger.Component
import dagger.Module
import javax.inject.Singleton


@Singleton
@Component(modules = [AppModule::class, SubcomponentsModule::class, ViewModelModule::class])
interface AppComponent {
    @Component.Factory
    interface Factory {
        fun create(): AppComponent

    }

    fun albumListComponent(): AlbumListComponent.Factory
    fun albumDetailComponent(): AlbumDetailComponent.Factory

}

@Module
object AppModule {

}

@Module(subcomponents = [AlbumListComponent::class])
object SubcomponentsModule