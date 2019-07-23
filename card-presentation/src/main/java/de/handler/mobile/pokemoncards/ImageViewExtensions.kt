package de.handler.mobile.pokemoncards

import android.widget.ImageView
import com.squareup.picasso.Picasso

fun ImageView.loadUrl(url: String?) {
    if (!url.isNullOrBlank()) {
        Picasso.get().load(url).into(this)
    }
}