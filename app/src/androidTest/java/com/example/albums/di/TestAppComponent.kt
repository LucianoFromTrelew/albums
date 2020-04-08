package com.example.albums.di

import dagger.Component
import dagger.Module
import javax.inject.Singleton

@Singleton
@Component(modules = [TestAppModule::class, SubcomponentsModule::class, ViewModelModule::class])
interface TestAppComponent : AppComponent

@Module
object TestAppModule