package com.example.albums.utils

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.example.albums.R
import com.example.albums.screens.albumlist.ApiStatus
import com.squareup.picasso.Picasso

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        Picasso.get().load(imgUrl).placeholder(R.drawable.loading_img)
            .error(R.drawable.ic_broken_image).into(imgView)
    }
}

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
