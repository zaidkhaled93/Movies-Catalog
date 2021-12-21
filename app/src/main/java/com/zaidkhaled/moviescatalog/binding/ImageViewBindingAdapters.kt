package com.zaidkhaled.moviescatalog.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.zaidkhaled.moviescatalog.extensions.loadImage

@BindingAdapter(value = ["imageUrl", "defaultImage"], requireAll = false)
fun setImageUrl(view: ImageView?, imageUrl: String?, defaultImage: Int) {
    view?.loadImage(imageUrl, defaultImage)
}