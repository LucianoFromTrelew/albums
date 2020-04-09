package com.example.albums.utils

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.example.albums.R
import com.example.albums.screens.albumlist.ApiStatus
import com.squareup.picasso.Picasso

/**
 * Uses the Glide library to load an image by URL into an [ImageView]
 */
@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        Picasso.get().load(imgUrl).placeholder(R.drawable.loading_img)
            .error(R.drawable.ic_broken_image).into(imgView)
    }
}

/**
 * This binding adapter displays the [MarsApiStatus] of the network request in an image view.  When
 * the request is loading, it displays a loading_animation.  If the request has an error, it
 * displays a broken image to reflect the connection error.  When the request is finished, it
 * hides the image view.
 */
@BindingAdapter("apiStatus")
fun bindStatus(statusImageView: ImageView, status: ApiStatus) {
    when (status) {
        ApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
        ApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        ApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
    }
}
