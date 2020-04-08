package com.example.albums

import com.example.albums.di.AppComponent
import com.example.albums.di.DaggerTestAppComponent

class MyTestApplication : MyApplication() {
    override fun initializeComponent(): AppComponent {
        return DaggerTestAppComponent.create()
    }
}