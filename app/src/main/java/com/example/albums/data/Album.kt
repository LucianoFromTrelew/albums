package com.example.albums.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Album(val id: String, val title: String): Parcelable