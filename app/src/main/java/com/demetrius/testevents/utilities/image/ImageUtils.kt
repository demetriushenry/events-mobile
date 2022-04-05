package com.demetrius.testevents.utilities.image

import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.squareup.picasso.Picasso

object ImageUtils {
    fun loadImage(view: ImageView?, url: String?) {
        if (url == null || url.isEmpty() || view == null) {
            return
        }
        Picasso.get().load(url).into(view)
    }

    fun loadImage(view: ImageView?, url: String?, @DrawableRes placeHolderId: Int) {
        if (url == null || url.isEmpty() || view == null) {
            view?.setImageResource(placeHolderId)
            return
        }
        Picasso.get().load(url).placeholder(placeHolderId).into(view)
    }
}