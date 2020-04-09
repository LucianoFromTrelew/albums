package com.example.albums.data.domain

import android.os.Parcelable
import com.example.albums.screens.DataBindingAdapter
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Album(override val id: String, val title: String) : Parcelable, DataBindingAdapter.HasId {
}