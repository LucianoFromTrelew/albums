package com.example.albums.di

import android.content.Context
import androidx.room.Room
import com.example.albums.data.source.*
import com.example.albums.data.source.local.LocalDataSource
import com.example.albums.data.source.local.MyDatabase
import com.example.albums.data.source.remote.MyApi
import com.example.albums.data.source.remote.RemoteDataSource
import com.example.albums.screens.albumdetail.AlbumDetailComponent
import com.example.albums.screens.albumlist.AlbumListComponent
import dagger.*
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Named
import javax.inject.Singleton


@Singleton
@Component(modules = [AppModule::class, SubcomponentsModule::class, ViewModelModule::class, RepositoryModule::class, NetworkModule::class])
interface AppComponent {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): AppComponent

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
    fun provideRemoteDataSource(api: MyApi): DataSource {
        return RemoteDataSource(api)
    }

    @JvmStatic
    @Singleton
    @Named(REPOSITORY_LOCAL_DATA_SOURCE)
    @Provides
    fun provideLocalDataSource(
        database: MyDatabase,
        ioDispatcher: CoroutineDispatcher
    ): DataSource {
        return LocalDataSource(database.dao(), ioDispatcher)
    }

    @JvmStatic
    @Singleton
    @Provides
    fun provideDatabase(context: Context): MyDatabase {
        return Room.databaseBuilder(context.applicationContext, MyDatabase::class.java, "Albums.db")
            .build()
    }

    @JvmStatic
    @Singleton
    @Provides
    fun provideIoDispatcher() = Dispatchers.IO
}

@Module
interface RepositoryModule {
    @Singleton
    @Binds
    fun bindRepository(repo: DefaultRepository): Repository
}

@Module(subcomponents = [AlbumListComponent::class, AlbumDetailComponent::class])
object SubcomponentsModule