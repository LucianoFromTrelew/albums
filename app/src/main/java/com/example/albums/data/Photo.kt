package com.example.albums.data

import android.os.Parcelable
import com.example.albums.DataBindingAdapter
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Photo(
    override val id: String,
    val title: String,
    val url: String,
    val thumbnailUrl: String
) : Parcelable, DataBindingAdapter.HasId