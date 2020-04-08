package com.example.albums.di

import com.example.albums.data.source.FakeRepository
import com.example.albums.data.source.Repository
import dagger.Binds
import dagger.Component
import dagger.Module
import javax.inject.Singleton

@Singleton
@Component(modules = [TestAppModule::class, SubcomponentsModule::class, ViewModelModule::class])
interface TestAppComponent : AppComponent

@Module
interface TestAppModule {

    @Singleton
    @Binds
    fun bindFakeRepository(repo: FakeRepository): Repository
}