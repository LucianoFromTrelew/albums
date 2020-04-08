package com.example.albums

import android.app.AppComponentFactory
import android.app.Application
import com.example.albums.di.AppComponent
import com.example.albums.di.DaggerAppComponent

class MyApplication : Application() {

    val appComponent: AppComponent by lazy {
        initializeComponent()
    }

    private fun initializeComponent(): AppComponent {
        return DaggerAppComponent.factory().create()
    }
}