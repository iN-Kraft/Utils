package dev.datlag.inkraft.fetcher.compat

import android.content.res.Resources
import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import dev.datlag.inkraft.fetcher.Fetcher

class DrawableFetcher internal constructor() : Fetcher.Standard<Int, Drawable?> {
    override operator fun get(@DrawableRes resource: Int): Drawable? = ContextCompat.getDrawable(context, resource)

    operator fun get(@DrawableRes resource: Int, theme: Resources.Theme?): Drawable? = context.resources.getDrawable(resource, theme)
}