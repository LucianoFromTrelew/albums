package com.example.albums.data.domain

import android.os.Parcelable
import com.example.albums.screens.DataBindingAdapter
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Photo(
    override val id: String,
    val albumId: String,
    val title: String,
    val url: String,
    val thumbnailUrl: String
) : Parcelable, DataBindingAdapter.HasId