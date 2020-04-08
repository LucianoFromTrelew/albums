package com.example.albums.di

import com.example.albums.data.source.remote.Api
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

const val BASE_URL = "http://jsonplaceholder.typicode.com/"
@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideMoshi(): Moshi {
        return Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(moshi: Moshi, coroutineCallAdapterFactory: CoroutineCallAdapterFactory): Retrofit {
        return Retrofit.Builder().addConverterFactory(MoshiConverterFactory.create(moshi)).addCallAdapterFactory(coroutineCallAdapterFactory).baseUrl(BASE_URL).build()
    }

    @Singleton
    @Provides
    fun provideApi(retrofit: Retrofit): Api {
        return retrofit.create(Api::class.java)
    }
}