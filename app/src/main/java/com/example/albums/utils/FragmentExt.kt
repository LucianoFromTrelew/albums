package com.example.albums.utils

import androidx.fragment.app.Fragment
import com.example.albums.MyApplication
import com.example.albums.di.AppComponent

val Fragment.appComponent: AppComponent
    get() = (requireActivity().application as MyApplication).appComponent
