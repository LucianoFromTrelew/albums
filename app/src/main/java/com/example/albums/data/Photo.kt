package com.example.albums.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Photo(
    val id: String,
    val title: String,
    val url: String,
    val thumbnailUrl: String
) : Parcelable