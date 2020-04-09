package com.example.albums.di

import com.example.albums.data.source.DataSource
import com.example.albums.data.source.DefaultRepository
import com.example.albums.data.source.REPOSITORY_REMOTE_DATA_SOURCE
import com.example.albums.data.source.Repository
import com.example.albums.data.source.remote.Api
import com.example.albums.data.source.remote.RemoteDataSource
import com.example.albums.screens.albumdetail.AlbumDetailComponent
import com.example.albums.screens.albumlist.AlbumListComponent
import dagger.Binds
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton


@Singleton
@Component(modules = [AppModule::class, SubcomponentsModule::class, ViewModelModule::class, RepositoryModule::class, NetworkModule::class])
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

    @JvmStatic
    @Singleton
    @Named(REPOSITORY_REMOTE_DATA_SOURCE)
    @Provides
    fun provideRemoteDataSource(api: Api): DataSource {
        return RemoteDataSource(api)
    }
}

@Module
interface RepositoryModule {
    @Singleton
    @Binds
    fun bindRepository(repo: DefaultRepository): Repository
}

@Module(subcomponents = [AlbumListComponent::class, AlbumDetailComponent::class])
object SubcomponentsModule