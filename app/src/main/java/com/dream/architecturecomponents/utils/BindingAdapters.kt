package com.dream.architecturecomponents.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.dream.architecturecomponents.R
import com.squareup.picasso.Picasso

@BindingAdapter("cover")
fun cover(view: ImageView, coverUrl: String?) {
    Picasso.get()
        .load("https://image.tmdb.org/t/p/original/$coverUrl")
        .error(R.drawable.ic_error_black_24dp)
        .into(view)
}